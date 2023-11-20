package org.shop.yogizogi_android.ui.view.auth.profile

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentProfileNicknameBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class ProfileNicknameFragment : BaseFragment<FragmentProfileNicknameBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_nickname
) {
    override fun initView() {

    }

    override fun initAfterBinding() {

    }
}