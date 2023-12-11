package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.request.auth.AddMapReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.AddMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO

interface StoreRemoteDataSource {
    suspend fun getRestaurants(
        header: String,
        moods: Array<String>
    ): Resource<List<SpecificStoreResDTO>>

    suspend fun getSpecificRestaurant(
        header: String,
        restaurantId: String
    ): Resource<SpecificStoreResDTO>

    suspend fun postAddMapRestaurants(
        header: String,
        body: AddMapReqDTO
    ): Resource<AddMapResDTO>

    suspend fun deleteMapRestaurants(
        header: String,
        body: AddMapReqDTO
    ): Resource<String>
}