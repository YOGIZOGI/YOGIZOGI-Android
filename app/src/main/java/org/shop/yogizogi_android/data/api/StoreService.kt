package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.auth.AddMapReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.auth.AddMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import org.shop.yogizogi_android.di.ACCESS_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreService {
    @GET("/api/restaurants")
    suspend fun getRestaurants(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Query("yogiMoods") moods: Array<String>
    ): Response<CommonSuccessRes<List<SpecificStoreResDTO>>>

    @GET("/api/restaurants/{restaurantId}")
    suspend fun getSpecificRestaurant(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Path("restaurantId") restaurantId: String
    ): Response<CommonSuccessRes<SpecificStoreResDTO>>

    @POST("/api/meok-maps/add/restaurants")
    suspend fun postAddMapRestaurants(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: AddMapReqDTO
    ): Response<CommonSuccessRes<AddMapResDTO>>

    @DELETE("/api/meok-maps/remove/restaurants")
    suspend fun deleteMapRestaurants(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: AddMapReqDTO
    ): Response<CommonSuccessRes<String>>
}