package org.shop.yogizogi_android.ui.view.main.home.storeinfo

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentStoreInfoBinding
import org.shop.yogizogi_android.ui.adapter.StoreInfoVPAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel

@AndroidEntryPoint
class StoreInfoFragment : BaseFragment<FragmentStoreInfoBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_store_info
) {
    private lateinit var storeInfoVPAdapter: StoreInfoVPAdapter

    private val imgList by lazy {
        arrayListOf(
            "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fblog.naver.com%2Feomji_123%2F220974335217%3FviewType%3Dpc&psig=AOvVaw1bEnhCJWzgONT7ZgMD51tc&ust=1701496066814000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPja8_bE7YIDFQAAAAAdAAAAABAI",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fsoundcloud.com%2Fcosmosound%2Fsz9qmsgteudj&psig=AOvVaw1bEnhCJWzgONT7ZgMD51tc&ust=1701496066814000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPja8_bE7YIDFQAAAAAdAAAAABAR"
        )
    }

    override fun initView() {
        storeInfoVPAdapter=StoreInfoVPAdapter(imgList)
        binding.vpStoreImage.adapter = storeInfoVPAdapter
    }

    override fun initAfterBinding() {

    }
}