package org.shop.yogizogi_android.ui.view.profile.inner

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    // TODO ProfileIntroFragment로 넘어갈 때에 사용자의 닉네임 중복확인을 함과 동시에 S3에 객체 등록 요청 보내기
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
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    requireContext(),
                                    resources.getString(R.string.nickname_not_exist),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            navigateToProfileIntroFragment()
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun navigateToProfileIntroFragment() {
        val nickname = binding.etUserNickname.text.toString()
        findNavController().navigate(
            ProfileNicknameFragmentDirections.actionProfileNicknameFragmentToProfileIntroFragment(
                nickname
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewLifecycleOwner.lifecycleScope.coroutineContext.cancel()
    }
}

/**
 * KeyPoint
 * 일반적으로 viewLifecycleOwner.lifecycleScope를 사용하면 현재 View의 생명주기에 맞는 CoroutineScope가 생성.
 * 이 Scope내에서 실행되는 Coroutine은 Fragment의 View 생명주기에 따라 생성 및 취소가 됨.
 * 따라서 명시적으로 직접 onDestroyView에 Coroutine을 취소할 필요가 없지만 특별한 상황이나 특정한 로직에 따라 수동으로 취소해야할 때도 필요함.
 *
 * 특정한 상황? ==> 비동기 작업을 명시적으로 종료하고 싶을 때, Fragment의 다른 생명주기 이벤트(onPause, onStop, onDestroy, onDestroyView)에 따른 작업을 수행할 때
 *
 * 위와 같은 경우에는 viewModel.nicknameDupProcess.collect가 View가 종료된 후에도 계속해서 수집을 하기에 오류가 생긴듯 함.
 * 해결)
 * 1. repeatOnLifecycle: 블록이 STARTED 상태일 때만 실행되도록 보장함. STARTED 상태가 아니라면 실행X.
 *      이를 미 사용시 viewModel.nicknameDupProcess.collect가 Fragment의 생명주기와 무관하게 계속 실행됨.
 *      따라서 STARTED 상태에서만 동작하도록 제한
 * 2. onDestroyView에서 명시적으로 Coroutine 작업 취소
 *
 * @author Wbham
 */
