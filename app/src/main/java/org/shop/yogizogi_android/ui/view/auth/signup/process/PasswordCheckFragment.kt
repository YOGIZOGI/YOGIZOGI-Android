package org.shop.yogizogi_android.ui.view.auth.signup.process

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentPasswordCheckBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel
import org.shop.yogizogi_android.ui.view.main.MainActivity

@AndroidEntryPoint
class PasswordCheckFragment : BaseFragment<FragmentPasswordCheckBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_password_check
) {
    override fun initView() {
        initBackBtn()
        watchPasswordCheck()
        btnSignUpComplete()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.signUpProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Toast.makeText(
                            requireContext(),
                            resources.getString(R.string.signup_complete),
                            Toast.LENGTH_SHORT
                        ).show()
                        val parentFragment = parentFragment
                        parentFragment?.findNavController()
                            ?.navigate(R.id.action_signUpFragment_to_initialFragment)
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun btnSignUpComplete() {
        binding.btnComplete.setOnClickListener {
            if (viewModel.passwordCheck.value.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_password),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (it.isActivated) {
                viewModel.signUp()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun watchPasswordCheck() {
        binding.tilEtPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val passwordCheck = editable.toString()
                viewModel.updatePasswordCheck(passwordCheck)
                if (passwordCheck.isEmpty() || isPasswordSame()) {
                    binding.tilLayout.error = null
                    binding.btnComplete.isActivated = true
                } else {
                    binding.tilLayout.error =
                        resources.getString(R.string.signup_error_password_check)
                    binding.btnComplete.isActivated = false
                }
            }
        })
    }

    private fun isPasswordSame(): Boolean {
        return viewModel.password.value == viewModel.passwordCheck.value
    }

    private fun initBackBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.stepDown()
        }
    }
}