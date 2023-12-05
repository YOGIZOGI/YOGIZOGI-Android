package org.shop.yogizogi_android.ui.view.profile.inner

import android.widget.Toast
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
import org.shop.yogizogi_android.databinding.FragmentProfileIntroBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.profile.ProfileViewModel

@AndroidEntryPoint
class ProfileIntroFragment : BaseFragment<FragmentProfileIntroBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_intro
) {
    // TODO 이 부분에서 닉네임과 프로필 사진, 소개글을 서버에 전송
    private val navArgs: ProfileIntroFragmentArgs by navArgs()
    override fun initView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        skip()
        createUserProfile()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userProfileProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        playAnimation(binding.lottieLoading)
                    }

                    is Resource.Success -> {
                        stopAnimation(binding.lottieLoading)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                resources.getString(R.string.profile_create),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navigateToProfileTasteFrag()
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

    private fun createUserProfile() {
        binding.btnNext.setOnClickListener {
            val nickname = navArgs.nickname
            val userImage = ""
            val userIntro = binding.etUserIntro.text.toString()
            viewModel.createUserProfile(nickname, userImage, userIntro)
        }
    }

    private fun skip() {
        binding.btnSkip.setOnClickListener {
            val nickname = navArgs.nickname
            val userImage = ""
            val userIntro = ""
            viewModel.createUserProfile(nickname, userImage, userIntro)
        }
    }

    // TODO btnNext 요청 보내고 성공시 navigate
    private fun navigateToProfileTasteFrag() {
        val nickname = navArgs.nickname
        findNavController().navigate(
            ProfileIntroFragmentDirections.actionProfileIntroFragmentToProfileTasteFragment(
                nickname
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewLifecycleOwner.lifecycleScope.coroutineContext.cancel()
    }
}