package org.shop.yogizogi_android.ui.view.main.home.storereviews

import android.util.Log
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.local.StoreReview
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

    private val storeReviewList by lazy {
        arrayListOf<StoreReview>(
            StoreReview(
                "욕죡",
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

    override fun initView() {
        val navArgs = navArgs.storeInfo
        Log.d("StoreReviewFrag-navArgs", navArgs.toString())
        initAdapter()
    }

    override fun initAfterBinding() {

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
        storeReviewAdapter.submitList(storeReviewList)
    }
}