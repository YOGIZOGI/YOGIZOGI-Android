package org.shop.yogizogi_android.ui.view.profile.inner

import androidx.navigation.fragment.findNavController
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
    override fun initView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_profileIntroFragment_to_profileTasteFragment)
        }
    }

    override fun initAfterBinding() {

    }
}