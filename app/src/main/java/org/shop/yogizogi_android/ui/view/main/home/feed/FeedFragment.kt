package org.shop.yogizogi_android.ui.view.main.home.feed

import android.util.Log
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentFeedBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_feed
) {
    private val navArgs: FeedFragmentArgs by navArgs()

    override fun initView() {
        val searchText = navArgs.searchText ?: "null"
        val mood = navArgs.mood
        Log.d("FeedFrag", "검색어: $searchText, 선택한 분위기: $mood")
    }

    override fun initAfterBinding() {

    }
}