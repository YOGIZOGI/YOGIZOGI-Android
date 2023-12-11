package org.shop.yogizogi_android.data.api

import org.shop.yogizogi_android.data.model.remote.request.auth.MenuReviewReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.ReviewReqDTO
import org.shop.yogizogi_android.data.model.remote.request.auth.ServiceReviewReqDTO
import org.shop.yogizogi_android.data.model.remote.response.CommonSuccessRes
import org.shop.yogizogi_android.data.model.remote.response.auth.MenuReviewResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.OneMenuReviewResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.ReviewResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificMenuReviewResDTO
import org.shop.yogizogi_android.di.ACCESS_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewService {
    @POST("/api/reviews/service-reviews/create")
    suspend fun postServiceReview(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: ServiceReviewReqDTO
    ): Response<CommonSuccessRes<MenuReviewResDTO>>

    @POST("/api/reviews/menu-reviews/create")
    suspend fun postMenuReview(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: MenuReviewReqDTO
    ): Response<CommonSuccessRes<MenuReviewResDTO>>

    @POST("/api/reviews/create")
    suspend fun postReview(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Body body: ReviewReqDTO
    ): Response<CommonSuccessRes<ReviewResDTO>>

    @GET("/api/reviews/menu-reviews/{menuReviewId}")
    suspend fun getOneMenuReview(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Path("menuReviewId") menuReviewId: Long
    ): Response<CommonSuccessRes<OneMenuReviewResDTO>>

    @GET("/api/reviews/menu-reviews/menus/{menuId}")
    suspend fun getSpecificMenuReviews(
        @Header(ACCESS_TOKEN) accessToken: String,
        @Path("menuId") menuId: Long
    ): Response<CommonSuccessRes<SpecificMenuReviewResDTO>>
}