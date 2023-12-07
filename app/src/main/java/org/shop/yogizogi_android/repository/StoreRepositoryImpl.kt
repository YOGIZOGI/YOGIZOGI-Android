package org.shop.yogizogi_android.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.datasource.StoreRemoteData
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val storeRemoteData: StoreRemoteData) :
    StoreRepository {
    override suspend fun getStoreWithMoods(
        header: String,
        moods: Array<String>
    ): Flow<Resource<List<SpecificStoreResDTO>>> {
        return flow {
            emit(storeRemoteData.getRestaurants(header, moods))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSpecificStore(
        header: String,
        restaurantId: String
    ): Flow<Resource<SpecificStoreResDTO>> {
        return flow {
            emit(storeRemoteData.getSpecificRestaurant(header, restaurantId))
        }.flowOn(Dispatchers.IO)
    }
}