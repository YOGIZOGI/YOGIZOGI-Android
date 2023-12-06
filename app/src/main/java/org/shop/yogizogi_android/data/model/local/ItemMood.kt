package org.shop.yogizogi_android.data.model.local

import org.shop.yogizogi_android.utils.Moods
import java.io.Serializable

data class ItemMood(
    val moodTitle: String,
    val moodEnum: Moods
) : Serializable
