package org.shop.yogizogi_android.ui.view.main.home.storeinfo

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.local.StoreInfoMenu
import org.shop.yogizogi_android.databinding.FragmentStoreInfoBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.StoreInfoVPAdapter
import org.shop.yogizogi_android.ui.adapter.StoreMenuAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class StoreInfoFragment : BaseFragment<FragmentStoreInfoBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_store_info
) {
    private lateinit var storeInfoVPAdapter: StoreInfoVPAdapter
    private lateinit var storeMenuAdapter: StoreMenuAdapter

    private val imgList by lazy {
        arrayListOf(
            "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
            "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
            "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg"
        )
    }

    private val menuList by lazy {
        arrayListOf<StoreInfoMenu>(
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "12,000원"
            ),
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "16,000원"
            ),
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "12,000원"
            ),
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "11,000원"
            ),
            StoreInfoMenu(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "큐브 스테이크 덮밥",
                "14,000원"
            ),
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "13,800원"
            ),
            StoreInfoMenu(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "큐브 스테이크 덮밥",
                "19,000원"
            ),
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "12,500원"
            ),
            StoreInfoMenu(
                "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg",
                "큐브 스테이크 덮밥",
                "13,500원"
            )
        )
    }

    override fun initView() {
        initBackBtn()
        initAdapter()
        storeInfoVPAdapter = StoreInfoVPAdapter(imgList)
        binding.vpStoreImage.adapter = storeInfoVPAdapter
    }

    override fun initAfterBinding() {

    }

    private fun initAdapter() {
        storeMenuAdapter = StoreMenuAdapter()
        binding.rvMenuList.adapter = storeMenuAdapter
        binding.rvMenuList.addItemDecoration(
            ItemDecoration(
                left = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                right = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                top = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                bottom = resources.getDimension(R.dimen.item_space_start).roundToInt()
            )
        )
        storeMenuAdapter.submitList(menuList)
    }

    private fun initBackBtn() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}