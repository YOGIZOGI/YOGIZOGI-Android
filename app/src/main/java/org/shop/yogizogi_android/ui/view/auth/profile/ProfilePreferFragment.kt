package org.shop.yogizogi_android.ui.view.auth.profile

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentProfilePreferBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class ProfilePreferFragment : BaseFragment<FragmentProfilePreferBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_prefer
) {
    override fun initView() {

    }

    override fun initAfterBinding() {

    }
}