package org.shop.yogizogi_android.data.model.remote.request.auth

data class TasteRegisterReqDTO(
    val spicyPreference: Long,
    val spicyIntensity: Long,
    val saltyPreference: Long,
    val saltyIntensity: Long,
    val sweetnessPreference: Long,
    val sweetnessIntensity: Long
)
