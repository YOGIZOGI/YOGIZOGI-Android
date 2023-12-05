package org.shop.yogizogi_android.ui.view.main.home.feed

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.local.MainFeed
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

    private val mainFeedList by lazy {
        arrayListOf<MainFeed>(
            MainFeed(null, resources.getString(R.string.mood_date)),
            MainFeed(null, resources.getString(R.string.mood_free)),
            MainFeed(null, resources.getString(R.string.mood_sum)),
            MainFeed(null, resources.getString(R.string.mood_nopo)),
            MainFeed(null, resources.getString(R.string.mood_group)),
            MainFeed(null, resources.getString(R.string.mood_parent)),
            MainFeed(null, resources.getString(R.string.mood_light)),
            MainFeed(null, resources.getString(R.string.mood_solo)),
            MainFeed(null, resources.getString(R.string.mood_marry)),
            MainFeed(null, resources.getString(R.string.mood_study))
        )
    }

    override fun initView() {
        val searchText = navArgs.searchText ?: "null"
        val mood = navArgs.mood
        Log.d("FeedFrag", "검색어: $searchText, 선택한 분위기: $mood")
        initAdapter()
    }

    override fun initAfterBinding() {

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
        feedAdapter.submitList(mainFeedList)
    }

    override fun onItemClick(item: MainFeed) {
        navigateToStoreReviewFragment(item)
    }

    private fun navigateToStoreReviewFragment(item: MainFeed) {
        findNavController().navigate(
            FeedFragmentDirections.actionFeedFragmentToStoreReviewFragment(
                item
            )
        )
    }
}