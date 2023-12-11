package org.shop.yogizogi_android.data.model.remote.response.auth

import java.io.Serializable

data class SpecificStoreResDTO(
    val id: String,
    val restaurantDetails: RestaurantDetails,
    val menus: List<MenuVO>?
) : Serializable

data class MenuVO(
    val id: Long,
    val details: MenuDatails
)

data class MenuDatails(
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String
)

data class FeedList(
    val feedList: List<SpecificStoreResDTO>
) : Serializable