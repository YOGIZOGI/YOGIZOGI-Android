package org.shop.yogizogi_android.ui.view.main.home.storeinfo

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
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
    private val navArgs: StoreInfoFragmentArgs by navArgs()
    private lateinit var storeInfoVPAdapter: StoreInfoVPAdapter
    private lateinit var storeMenuAdapter: StoreMenuAdapter

    override fun initView() {
        initBackBtn()
        initAdapter()
        initStoreDetail()
        initCreateReviewBtn()
    }

    override fun initAfterBinding() {

    }

    private fun initStoreDetail() {
        val storeDetail = navArgs.storeDetail
        if (storeDetail != null) {
            with(binding) {
                tvTitleStoreName.text = storeDetail.restaurantDetails.name
                tvStoreAddress.text = storeDetail.restaurantDetails.address
                tvStoreNumber.text = storeDetail.restaurantDetails.tel

                val imgList by lazy {
                    arrayListOf(
                        storeDetail.restaurantDetails.imageUrl,
                        "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                        "https://cdn.dealbada.com/data/editor/1704/e8bc3ff4a802bec4c500fcdca76f75b2_1491903567_2258.jpg"
                    )
                }
                storeInfoVPAdapter = StoreInfoVPAdapter(imgList)
                vpStoreImage.adapter = storeInfoVPAdapter

                storeMenuAdapter.submitList(storeDetail.menus)
            }
        }
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
    }

    private fun initCreateReviewBtn() {
        binding.ivWriteReview.setOnClickListener {
            findNavController().navigate(StoreInfoFragmentDirections.actionStoreInfoFragmentToCreateReviewFragment())
        }
    }

    private fun initBackBtn() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}