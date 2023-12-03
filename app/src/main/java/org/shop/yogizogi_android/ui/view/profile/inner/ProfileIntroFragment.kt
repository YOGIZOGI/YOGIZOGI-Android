package org.shop.yogizogi_android.ui.view.profile.inner

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
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
        navigateToProfileTasteFrag()
        skip()
        registUserProfile()
    }

    override fun initAfterBinding() {

    }

    private fun registUserProfile() {
        val userIntro = binding.etUserIntro.text.toString()
    }

    private fun skip() {
        binding.btnSkip.setOnClickListener {
            val nickname = navArgs.nickname
            findNavController().navigate(
                ProfileIntroFragmentDirections.actionProfileIntroFragmentToProfileTasteFragment(
                    nickname
                )
            )
        }
    }

    private fun navigateToProfileTasteFrag() {
        binding.btnNext.setOnClickListener {
            val nickname = navArgs.nickname
            findNavController().navigate(
                ProfileIntroFragmentDirections.actionProfileIntroFragmentToProfileTasteFragment(
                    nickname
                )
            )
        }
    }
}