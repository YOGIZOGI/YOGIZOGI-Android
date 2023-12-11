package org.shop.yogizogi_android.ui.view.main.home.feed

import android.content.Context
import android.util.Log
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.remote.response.auth.FeedList
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import org.shop.yogizogi_android.databinding.FragmentFeedBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.MainFeedAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel
import org.shop.yogizogi_android.utils.clicklistener.MainFeedClick
import kotlin.math.roundToInt

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_feed
), MainFeedClick {
    private val navArgs: FeedFragmentArgs by navArgs()
    private lateinit var feedAdapter: MainFeedAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.initMoodStoreProcess()
            findNavController().popBackStack()
        }
    }

    override fun initView() {
        val feedList = navArgs.feedList
        val searchText = navArgs.searchText ?: "null"
        val mood = navArgs.mood

        Log.d("FeedFrag", "검색어: $searchText, 선택한 분위기: $mood")
        initAdapter()
        initFeedList(feedList!!)
    }

    override fun initAfterBinding() {

    }

    private fun initFeedList(feedList: FeedList) {
        feedAdapter.submitList(feedList.feedList)
    }

    private fun initAdapter() {
        feedAdapter = MainFeedAdapter(this)
        binding.rvFeedList.adapter = feedAdapter
        binding.rvFeedList.addItemDecoration(
            ItemDecoration(
                left = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                right = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                top = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                bottom = resources.getDimension(R.dimen.item_space_start).roundToInt()
            )
        )
    }

    override fun onItemClick(item: SpecificStoreResDTO) {
        navigateToStoreReviewFragment(item)
    }

    private fun navigateToStoreReviewFragment(item: SpecificStoreResDTO) {
        findNavController().navigate(
            FeedFragmentDirections.actionFeedFragmentToStoreReviewFragment(
                item, null
            )
        )
    }
}