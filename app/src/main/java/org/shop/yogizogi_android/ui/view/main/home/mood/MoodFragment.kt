package org.shop.yogizogi_android.ui.view.main.home.mood

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.local.ItemMood
import org.shop.yogizogi_android.databinding.FragmentMoodBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.MoodAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel
import org.shop.yogizogi_android.utils.clicklistener.MoodItemClick
import kotlin.math.roundToInt

@AndroidEntryPoint
class MoodFragment : BaseFragment<FragmentMoodBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_mood
), MoodItemClick {
    private val navArgs: MoodFragmentArgs by navArgs()
    private lateinit var moodAdapter: MoodAdapter

    private val moodList by lazy {
        arrayListOf<ItemMood>(
            ItemMood(resources.getString(R.string.mood_date)),
            ItemMood(resources.getString(R.string.mood_free)),
            ItemMood(resources.getString(R.string.mood_sum)),
            ItemMood(resources.getString(R.string.mood_nopo)),
            ItemMood(resources.getString(R.string.mood_group)),
            ItemMood(resources.getString(R.string.mood_parent)),
            ItemMood(resources.getString(R.string.mood_light)),
            ItemMood(resources.getString(R.string.mood_solo)),
            ItemMood(resources.getString(R.string.mood_marry)),
            ItemMood(resources.getString(R.string.mood_study))
        )
    }

    override fun initView() {
        val navArgs = navArgs.searchText ?: "null"
        Log.d("moodFrag - searchText", navArgs)
        initAdapter()
    }

    override fun initAfterBinding() {

    }

    private fun initAdapter() {
        moodAdapter = MoodAdapter(this)
        binding.rvMoodList.adapter = moodAdapter
        binding.rvMoodList.addItemDecoration(
            ItemDecoration(
                left = resources.getDimension(R.dimen.item_space_start).roundToInt(),
                right = resources.getDimension(R.dimen.item_space_end).roundToInt(),
                top = resources.getDimension(R.dimen.item_space_top).roundToInt(),
                bottom = resources.getDimension(R.dimen.item_space_bottom).roundToInt()
            )
        )
        moodAdapter.submitList(moodList)
    }

    override fun onItemClick(item: ItemMood) {
        navigateToFeedFragment(item.moodTitle)
    }

    private fun navigateToFeedFragment(moodTitle: String) {
        val searchText = navArgs.searchText ?: "null"
        findNavController().navigate(
            MoodFragmentDirections.actionMoodFragmentToFeedFragment(
                searchText,
                moodTitle
            )
        )
    }
}