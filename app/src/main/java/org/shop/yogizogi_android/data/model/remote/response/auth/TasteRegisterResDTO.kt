package org.shop.yogizogi_android.data.model.remote.response.auth

data class TasteRegisterResDTO(
    val preference: Preference,
    val intensity: Intensity
)

data class Preference(
    val spicyPreference: Long,
    val saltyPreference: Long,
    val sweetnessPreference: Long
)

data class Intensity(
    val spicyIntensity: Long,
    val saltyIntensity: Long,
    val sweetnessIntensity: Long
)