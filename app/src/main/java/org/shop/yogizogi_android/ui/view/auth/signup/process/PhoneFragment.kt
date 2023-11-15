package org.shop.yogizogi_android.ui.view.auth.signup.process

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentPhoneBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel

@AndroidEntryPoint
class PhoneFragment : BaseFragment<FragmentPhoneBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_phone
) {
    override fun initView() {
        initBackBtn()
        watchPhoneNumber()
        sendRequest()
    }

    private fun sendRequest() {
        binding.btnRequest.setOnClickListener {
            if (viewModel.phoneNumber.value.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_button),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (it.isActivated) {
                viewModel.getVerifyCode()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_button),
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
            viewModel.codeReqProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Toast.makeText(
                            requireContext(),
                            result.data.description,
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
                        viewModel.stepUp()
                    }
                }
            }
        }
    }

    private fun watchPhoneNumber() {
        binding.tilEtPhonenum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val phoneNumber = editable.toString()
                viewModel.updatePhoneNumber(phoneNumber)
                if (phoneNumber.isEmpty() || isPhoneNumberValid(phoneNumber)) {
                    binding.tilLayout.error = null
                    binding.btnRequest.isActivated = true
                } else {
                    binding.tilLayout.error = resources.getString(R.string.signup_error_default)
                    binding.btnRequest.isActivated = false
                }
            }
        })
    }

    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        val phoneRegex = """^010\d{8}${'$'}""".toRegex()
        return phoneRegex.matches(phoneNumber)
    }

    private fun initBackBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }
}