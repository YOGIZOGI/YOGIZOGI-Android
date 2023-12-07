package org.shop.yogizogi_android.ui.view.main.home.mood

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.data.model.remote.response.auth.FeedList
import org.shop.yogizogi_android.databinding.FragmentMoodBinding
import org.shop.yogizogi_android.ui.adapter.ItemDecoration
import org.shop.yogizogi_android.ui.adapter.MoodAdapter
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.home.HomeViewModel
import org.shop.yogizogi_android.utils.Moods
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
        arrayListOf<Moods>(
            Moods.SOLO,
            Moods.WITH_LOVER,
            Moods.WITH_FRIENDS,
            Moods.WITH_PARENT,
            Moods.WITH_CHILD,
            Moods.WITH_COLLEAGUE,
            Moods.LIGHT_MEAL,
            Moods.GOURMET_MEAL,
            Moods.PAIRING_MEAL,
            Moods.BUSINESS_MEETING,
            Moods.GROUP_MEETING,
            Moods.ANNIVERSARY
        )
    }

    override fun initView() {
        val navArgs = navArgs.searchText ?: "null"
        Log.d("moodFrag - searchText", navArgs)
        initAdapter()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moodStoreProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        playAnimation(binding.lottieLoading)
                    }

                    is Resource.Success -> {
                        stopAnimation(binding.lottieLoading)
                        val feedList = FeedList(result.data)
                        navigateToFeedFragment(feedList)
                    }

                    is Resource.Error -> {
                        stopAnimation(binding.lottieLoading)

                        // TODO 임시로 Error여도 navigate임시로 Error여도 navigate
//                        navigateToFeedFragment()
                        withContext(Dispatchers.Main) {
                            showToast(result.message)
                        }
                    }

                    null -> {}
                }
            }
        }
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

    override fun onItemClick(item: Moods) {
        Log.d("MoodFragment Clicked Mood", "item.name: ${item.name}, item.korean: ${item.korean}")
        viewModel.getStoreWithMood(item.name)
    }

    private fun navigateToFeedFragment(feedList: FeedList) {
        findNavController().navigate(
            MoodFragmentDirections.actionMoodFragmentToFeedFragment(
                navArgs.searchText,
                null,
                feedList,
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewLifecycleOwner.lifecycleScope.coroutineContext.cancel()
    }
}