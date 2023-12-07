package org.shop.yogizogi_android.ui.view.main.myreview

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.local.MyReview
import org.shop.yogizogi_android.data.model.local.StoreReview
import org.shop.yogizogi_android.data.model.remote.response.auth.Profile
import org.shop.yogizogi_android.data.toMyReview
import org.shop.yogizogi_android.databinding.FragmentReviewBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.MyReviewAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.utils.clicklistener.MyReviewClick
import kotlin.math.roundToInt

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding, ReviewViewModel>(
    ReviewViewModel::class.java,
    R.layout.fragment_review
), MyReviewClick {
    private lateinit var myReviewAdapter: MyReviewAdapter

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
                        val reviewData = result.data.menuReviews.map {
                            it.toMyReview()
                        }
                        myReviewAdapter.submitList(reviewData)
                        val userProfile = result.data.userProfile
                        setUserProfile(userProfile)
                    }

                    is Resource.Error -> {
                        stopAnimation(binding.lottieLoading)
                    }

                    null -> {}
                }
            }
        }
    }

    private fun setUserProfile(userProfile: Profile) {
        binding.tvUserNickname.text = userProfile.nickname
        if (userProfile.imageUrl != "") {
            Picasso.get().load(userProfile.imageUrl).placeholder(R.drawable.ic_user_default)
                .into(binding.ivUserImg)
        }
        binding.tvUserDesc.text = userProfile.introduction
    }

    private fun initAdapter() {
        myReviewAdapter = MyReviewAdapter(this)
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

    override fun onItemClick(item: MyReview) {
        Log.d("ReviewFragment Item Click", "클릭됨")
        val mappedItem =
            StoreReview(
                item.id.toString(),
                item.images,
                false,
                item.recommendationStatus,
                3,
                4,
                1,
                4,
                2,
                4,
                item.content
            )
        navigateToStoreReviewFragment(mappedItem)
    }

    private fun navigateToStoreReviewFragment(storeReview: StoreReview) {
        findNavController().navigate(
            ReviewFragmentDirections.actionReviewFragmentToStoreReviewFragment(null, storeReview)
        )
    }
}