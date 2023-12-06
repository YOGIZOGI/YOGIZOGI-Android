package org.shop.yogizogi_android.repository

import kotlinx.coroutines.flow.Flow
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO

interface StoreRepository {
    suspend fun getStoreWithMoods(
        header: String,
        moods: Array<String>
    ): Flow<Resource<SpecificStoreResDTO>>

    suspend fun getSpecificStore(
        header: String,
        restaurantId: String
    ): Flow<Resource<SpecificStoreResDTO>>
}