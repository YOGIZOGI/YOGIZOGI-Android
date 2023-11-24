package org.shop.yogizogi_android.ui.view.profile.inner

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentProfilePreferBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.profile.ProfileViewModel

@AndroidEntryPoint
class ProfilePreferFragment : BaseFragment<FragmentProfilePreferBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_prefer
) {
    override fun initView() {
//        val intent = Intent(requireContext(), MainActivity::class.java)
//        startActivity(intent)
//        requireActivity().finish()
    }

    override fun initAfterBinding() {

    }
}