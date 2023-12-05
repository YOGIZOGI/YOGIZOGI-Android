package org.shop.yogizogi_android.ui.view.auth.signup.process

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentVerifyBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel

@AndroidEntryPoint
class VerifyFragment : BaseFragment<FragmentVerifyBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_verify
) {
    override fun initView() {
        initBackBtn()
        watchCode()
        checkCode()
    }

    private fun checkCode() {
        binding.btnNext.setOnClickListener {
            if (it.isActivated) {
                viewModel.checkVerifyCode()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_code),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.codeCheckProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Toast.makeText(
                            requireContext(),
                            result.data.status,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.stepUp()
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                            .show()
                        /**
                         * 임시 코드(인증번호 오지 않아도 다음 화면으로 이동)
                         */
//                        viewModel.stepUp()
                    }

                    null -> {}
                }
            }
        }
    }

    private fun watchCode() {
        binding.tilEtCodenum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val code = editable.toString()
                viewModel.updateCode(code)
                if (code.isEmpty()) {
                    binding.tilLayout.error = resources.getString(R.string.signup_error_code)
                    binding.btnNext.isActivated = false
                } else {
                    binding.tilLayout.error = null
                    binding.btnNext.isActivated = true
                }
            }
        })
    }

    private fun initBackBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.stepDown()
        }
    }
}