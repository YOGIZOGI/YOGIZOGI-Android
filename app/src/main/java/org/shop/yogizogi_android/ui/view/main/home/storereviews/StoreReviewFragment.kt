package org.shop.yogizogi_android.ui.view.main.home.storereviews

import android.content.Context
import android.util.Log
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.local.StoreReview
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import org.shop.yogizogi_android.databinding.FragmentStoreReviewBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.StoreReviewAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class StoreReviewFragment : BaseFragment<FragmentStoreReviewBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_store_review
) {
    private val navArgs: StoreReviewFragmentArgs by navArgs()
    private lateinit var storeReviewAdapter: StoreReviewAdapter

    private val imgList by lazy {
        arrayListOf(
            "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fblog.naver.com%2Feomji_123%2F220974335217%3FviewType%3Dpc&psig=AOvVaw1bEnhCJWzgONT7ZgMD51tc&ust=1701496066814000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPja8_bE7YIDFQAAAAAdAAAAABAI",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fsoundcloud.com%2Fcosmosound%2Fsz9qmsgteudj&psig=AOvVaw1bEnhCJWzgONT7ZgMD51tc&ust=1701496066814000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPja8_bE7YIDFQAAAAAdAAAAABAR"
        )
    }

    private val storeReviewList by lazy {
        arrayListOf<StoreReview>(
            StoreReview(
                "욕죡",
                imgList,
                true,
                true,
                2,
                4,
                1,
                4,
                2,
                3,
                resources.getString(R.string.main_review_long_text)
            ),
            StoreReview(
                "요오기죠기",
                imgList,
                true,
                false,
                4,
                2,
                5,
                1,
                3,
                2,
                resources.getString(R.string.main_review_long_text)
            ),
            StoreReview(
                "요기죠오기",
                imgList,
                false,
                false,
                1,
                3,
                4,
                2,
                3,
                5,
                resources.getString(R.string.main_review_long_text)
            ),
            StoreReview(
                "요기죠기이이",
                imgList,
                true,
                true,
                3,
                5,
                1,
                4,
                2,
                3,
                resources.getString(R.string.main_review_long_text)
            ),
            StoreReview(
                "욕조욕이",
                imgList,
                false,
                true,
                3,
                1,
                2,
                5,
                3,
                3,
                resources.getString(R.string.main_review_long_text)
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun initView() {
        val navArgs = navArgs.storeInfo
        Log.d("StoreReviewFrag-navArgs", navArgs.toString())
        binding.tvStoreName.text = navArgs?.restaurantDetails?.name
        initAdapter()
        initList()
        initBackBtn()
        initInfoBtn()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storeInfoProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        playAnimation(binding.lottieLoading)
                    }

                    is Resource.Success -> {
                        stopAnimation(binding.lottieLoading)
                        navigateToStoreInfo(result.data)
                    }

                    is Resource.Error -> {
                        stopAnimation(binding.lottieLoading)
                    }

                    null -> {}
                }
            }
        }
    }

    private fun initAdapter() {
        storeReviewAdapter = StoreReviewAdapter()
        binding.rvStoreReviews.adapter = storeReviewAdapter
        binding.rvStoreReviews.addItemDecoration(
            ItemDecoration(
                left = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                right = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                top = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                bottom = resources.getDimension(R.dimen.item_space_start).roundToInt()
            )
        )
    }

    private fun initList() {
        val args = navArgs.storeReview
        if (args != null) {
            Log.d("StoreReviewFragment Args", "navArgs 존재함!")
            storeReviewAdapter.submitList(arrayListOf(args))
        } else {
            Log.d("StoreReviewFragment Args", "navArgs 존재 안 함!")
            storeReviewAdapter.submitList(storeReviewList)
        }
    }

    private fun initBackBtn() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initInfoBtn() {
        binding.btnStoreInfo.setOnClickListener {
            // TODO 임시 코드!
//            viewModel.getStoreInfo("11ee94cb-c19d-a4d1-8b1c-597b5b7cf2dd")
            viewModel.getStoreInfo(navArgs.storeInfo!!.id)
        }
    }

    private fun navigateToStoreInfo(storeDetail: SpecificStoreResDTO) {
        findNavController().navigate(
            StoreReviewFragmentDirections.actionStoreReviewFragmentToStoreInfoFragment(
                storeDetail
            )
        )
    }
}