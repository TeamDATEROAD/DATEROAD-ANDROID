package org.sopt.dateroad.domain.model

data class SignUp(
    val userSignUpInfo: UserSignUpInfo = UserSignUpInfo(),
    val image: String = "",
    val tag: List<String> = listOf()
)
