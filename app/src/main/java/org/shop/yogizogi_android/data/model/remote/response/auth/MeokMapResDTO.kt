package org.shop.yogizogi_android.data.model.remote.response.auth

data class MeokMapResDTO(
    val restaurantId: String,
    val restaurantDetails: RestaurantDetails
)

data class RestaurantDetails(
    val name: String,
    val tel: String,
    val address: String,
    val imageUrl: String,
    val coordinate: Coordinate
)

data class Coordinate(
    val latitude: String,
    val longitude: String
)
