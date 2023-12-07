package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
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
}