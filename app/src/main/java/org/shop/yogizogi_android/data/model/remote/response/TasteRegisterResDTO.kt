package org.shop.yogizogi_android.data.model.remote.response

import org.shop.yogizogi_android.data.model.Intensity
import org.shop.yogizogi_android.data.model.Preference

data class TasteRegisterResDTO(
    val preference: Preference,
    val intensity: Intensity
)
