package org.shop.yogizogi_android.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.datasource.StoreRemoteData
import org.shop.yogizogi_android.data.model.remote.request.auth.AddMapReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.AddMapResDTO
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

    override suspend fun addMapRestaurants(
        header: String,
        body: AddMapReqDTO
    ): Flow<Resource<AddMapResDTO>> {
        return flow {
            emit(storeRemoteData.postAddMapRestaurants(header, body))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteMapRestaurants(
        header: String,
        body: AddMapReqDTO
    ): Flow<Resource<String>> {
        return flow {
            emit(storeRemoteData.deleteMapRestaurants(header, body))
        }.flowOn(Dispatchers.IO)
    }
}