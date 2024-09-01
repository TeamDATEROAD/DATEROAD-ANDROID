package org.sopt.dateroad.data.repositoryimpl

import android.content.ContentResolver
import android.net.Uri
import javax.inject.Inject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.PROFILE_FORM_DATA_IMAGE
import org.sopt.dateroad.data.dataremote.util.ContentUriRequestBody
import org.sopt.dateroad.data.mapper.todata.toData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Auth
import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.model.SignIn
import org.sopt.dateroad.domain.model.SignUp
import org.sopt.dateroad.domain.repository.AuthRepository

class AuthRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun deleteSignOut(): Result<Unit> = runCatching {
        authRemoteDataSource.deleteSignOut()
    }

    override suspend fun deleteWithdraw(authCode: String?): Result<Unit> = runCatching {
        authRemoteDataSource.deleteWithdraw(requestWithdrawDto = RequestWithdrawDto(authCode))
    }

    override suspend fun getNicknameCheck(name: String): Result<Int> = runCatching {
        authRemoteDataSource.getNicknameCheck(name = name)
    }

    override suspend fun postSignIn(authorization: String, signIn: SignIn): Result<Auth> = runCatching {
        authRemoteDataSource.postSignIn(authorization = authorization, requestSignInDto = signIn.toData()).toDomain()
    }

    override suspend fun postSignUp(signUp: SignUp): Result<Auth> = runCatching {
        authRemoteDataSource.postSignUp(
            image = if (signUp.image.isEmpty()) null else ContentUriRequestBody(contentResolver = contentResolver, uri = Uri.parse(signUp.image)).toFormData(name = PROFILE_FORM_DATA_IMAGE),
            userSignUpData = Json.encodeToString(signUp.userSignUpInfo.toData()).toRequestBody("application/json".toMediaType()),
            tags = (Json.encodeToString(signUp.tag.toData()).substringAfter(":").substringBeforeLast("}")).toRequestBody("application/json".toMediaType())
        ).toDomain()
    }

    override suspend fun patchEditProfile(editProfile: EditProfile): Result<Unit> = runCatching {
        authRemoteDataSource.patchEditProfile(
            name = editProfile.name.toRequestBody(),
            tags = (Json.encodeToString(editProfile.tags.toData()).substringAfter(":").substringBeforeLast("}")).toRequestBody("application/json".toMediaType()),
            image = if (editProfile.image.isNullOrEmpty() || editProfile.image.startsWith("https://")) {
                null
            } else {
                ContentUriRequestBody(contentResolver = contentResolver, uri = Uri.parse(editProfile.image)).toFormData(name = PROFILE_FORM_DATA_IMAGE)
            }
        )
    }
}
