package org.shop.yogizogi_android.ui.view.main.myreview

import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.local.MyReview
import org.shop.yogizogi_android.databinding.FragmentReviewBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.MyReviewAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import kotlin.math.roundToInt

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding, ReviewViewModel>(
    ReviewViewModel::class.java,
    R.layout.fragment_review
) {
    private lateinit var myReviewAdapter: MyReviewAdapter
    private val reviewList by lazy {
        arrayListOf<MyReview>(
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "건대 홍콩포차",
                false
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "건대 1943",
                true
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "건대 뒷고기",
                true
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "재즈",
                false
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "겨울나그네",
                true
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "호식이 한마리 치킨",
                false
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "홍대 할리스",
                false
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "강남 지그재그",
                false
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "홍콩 와인바",
                true
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "군자닭",
                true
            ),
            MyReview(
                "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                "빽다방",
                false
            ),
        )
    }

    override fun initView() {
        initAdapter()
        viewModel.getUserFeed()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userFeedProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        playAnimation(binding.lottieLoading)
                    }

                    is Resource.Success -> {
                        stopAnimation(binding.lottieLoading)
                        val data = result.data.menuReviews
                        myReviewAdapter.submitList(reviewList)
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
        myReviewAdapter = MyReviewAdapter()
        binding.rvMyReview.adapter = myReviewAdapter
        binding.rvMyReview.addItemDecoration(
            ItemDecoration(
                left = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                right = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                top = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                bottom = resources.getDimension(R.dimen.item_space_start).roundToInt()
            )
        )
    }
}