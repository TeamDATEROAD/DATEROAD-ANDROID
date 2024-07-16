package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementsDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.ADVERTISEMENTS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.GET

interface AdvertisementService {
    @GET("$API/$VERSION/$ADVERTISEMENTS")
    suspend fun getHomeAdvertisements(): ResponseAdvertisementsDto
}