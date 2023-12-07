package org.shop.yogizogi_android.utils.clicklistener

import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO

interface MainFeedClick {
    fun onItemClick(item: SpecificStoreResDTO)
}