package org.shop.yogizogi_android.data.model.remote.datasource

import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.api.StoreService
import org.shop.yogizogi_android.data.model.remote.request.auth.AddMapReqDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.AddMapResDTO
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import javax.inject.Inject

class StoreRemoteData @Inject constructor(private val storeService: StoreService) :
    StoreRemoteDataSource {
    override suspend fun getRestaurants(
        header: String,
        moods: Array<String>
    ): Resource<List<SpecificStoreResDTO>> {
        return processCall { storeService.getRestaurants(header, moods) }
    }

    override suspend fun getSpecificRestaurant(
        header: String,
        restaurantId: String
    ): Resource<SpecificStoreResDTO> {
        return processCall { storeService.getSpecificRestaurant(header, restaurantId) }
    }

    override suspend fun postAddMapRestaurants(
        header: String,
        body: AddMapReqDTO
    ): Resource<AddMapResDTO> {
        return processCall { storeService.postAddMapRestaurants(header, body) }
    }

    override suspend fun deleteMapRestaurants(
        header: String,
        body: AddMapReqDTO
    ): Resource<String> {
        return processCall { storeService.deleteMapRestaurants(header, body) }
    }
}