package org.shop.yogizogi_android.ui.view.profile.inner

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentProfileTasteBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.profile.ProfileViewModel

@AndroidEntryPoint
class ProfileTasteFragment : BaseFragment<FragmentProfileTasteBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_taste
) {
    private val navArgs: ProfileTasteFragmentArgs by navArgs()
    override fun initView() {
        binding.tvUserNickname.text = navArgs.nickname
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        initTasteCreateBtn()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userTasteProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        playAnimation(binding.lottieLoading)
                    }

                    is Resource.Success -> {
                        stopAnimation(binding.lottieLoading)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                resources.getString(R.string.taste_create),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navigateToProfilePreferFragment()
                    }

                    is Resource.Error -> {
                        stopAnimation(binding.lottieLoading)
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    null -> {}
                }
            }
        }
    }

    private fun initTasteCreateBtn() {
        binding.btnNext.setOnClickListener {
            val hotWell = binding.sliderHot.value.toLong()
            val sweetWell = binding.sliderSweet.value.toLong()
            val saltyWell = binding.sliderSalty.value.toLong()
            viewModel.createUserTaste(hotWell, hotWell, sweetWell, sweetWell, saltyWell, saltyWell)
        }
    }

    private fun navigateToProfilePreferFragment() {
        findNavController().navigate(
            ProfileTasteFragmentDirections.actionProfileTasteFragmentToProfilePreferFragment(
                navArgs.nickname
            )
        )
    }
}