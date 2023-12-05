package org.shop.yogizogi_android.ui.view.profile.inner

import android.content.Intent
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentProfilePreferBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.MainActivity
import org.shop.yogizogi_android.ui.view.profile.ProfileViewModel

@AndroidEntryPoint
class ProfilePreferFragment : BaseFragment<FragmentProfilePreferBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_prefer
) {
    private val navArgs: ProfilePreferFragmentArgs by navArgs()
    override fun initView() {
        binding.tvUserNickname.text = navArgs.nickname
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun initAfterBinding() {

    }
}