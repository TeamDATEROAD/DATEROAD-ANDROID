package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseAdvertisementDetailDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.ADVERTISEMENTS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.ADVERTISEMENT_ID
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.GET
import retrofit2.http.Path

interface AdvertisementService {
    @GET("$API/$VERSION/$ADVERTISEMENTS/{$ADVERTISEMENT_ID}")
    suspend fun getAdvertisementDetail(
        @Path(ADVERTISEMENT_ID) advertisementId: Int
    ): ResponseAdvertisementDetailDto
}
