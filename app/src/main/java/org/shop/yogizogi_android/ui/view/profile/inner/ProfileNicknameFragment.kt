package org.shop.yogizogi_android.ui.view.profile.inner

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentProfileNicknameBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.profile.ProfileViewModel
import org.shop.yogizogi_android.utils.ExistState

@AndroidEntryPoint
class ProfileNicknameFragment : BaseFragment<FragmentProfileNicknameBinding, ProfileViewModel>(
    ProfileViewModel::class.java,
    R.layout.fragment_profile_nickname
) {
    override fun initView() {
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
        }
        initNextBtn()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun initNextBtn() {
        binding.btnNext.setOnClickListener {
            val nickname = binding.etUserNickname.text.toString()
            if (nickname.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.nickname_empty),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.nicknameDupCheck(nickname)
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nicknameDupProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        if (result.data.status == ExistState.EXIST.toString()) {
                            Toast.makeText(
                                requireContext(),
                                resources.getString(R.string.nickname_exist),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                resources.getString(R.string.nickname_not_exist),
                                Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigate(R.id.action_profileNicknameFragment_to_profileIntroFragment)
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    null -> {}
                }
            }
        }
    }

//    private fun navigateToIntroFragment() {
//        findNavController().navigate(R.id.action_profileNicknameFragment_to_profileIntroFragment)
//    }
}