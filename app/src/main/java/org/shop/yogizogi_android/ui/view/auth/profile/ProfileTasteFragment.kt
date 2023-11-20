package org.shop.yogizogi_android.ui.view.auth.profile

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentProfileTasteBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class ProfileTasteFragment : BaseFragment<FragmentProfileTasteBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_taste
) {
    override fun initView() {

    }

    override fun initAfterBinding() {

    }
}