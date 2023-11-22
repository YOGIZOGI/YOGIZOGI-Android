package org.shop.yogizogi_android.ui.view.profile.inner

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

    }

    override fun initAfterBinding() {

    }
}