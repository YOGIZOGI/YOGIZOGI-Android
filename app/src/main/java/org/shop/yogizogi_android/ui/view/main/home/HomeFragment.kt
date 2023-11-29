package org.shop.yogizogi_android.ui.view.main.home

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentHomeBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_home
) {
    override fun initView() {
        setSearch()
    }

    override fun initAfterBinding() {

    }

    private fun setSearch() {
        binding.ivSearch.setOnClickListener {
            val searchText = binding.etSearchPlace.text.toString()
            navigateToMoodFragment(searchText)
        }
    }

    private fun navigateToMoodFragment(searchText: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMoodFragment(
                searchText
            )
        )
    }
}