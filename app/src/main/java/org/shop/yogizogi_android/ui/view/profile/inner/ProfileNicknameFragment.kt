package org.shop.yogizogi_android.ui.view.profile.inner

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentProfileNicknameBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.profile.ProfileViewModel

@AndroidEntryPoint
class ProfileNicknameFragment : BaseFragment<FragmentProfileNicknameBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_nickname
) {
    override fun initView() {
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_profileNickNameFragment_to_profileIntroFragment)
        }
    }

    override fun initAfterBinding() {

    }
}