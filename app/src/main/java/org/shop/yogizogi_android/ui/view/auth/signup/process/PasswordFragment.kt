package org.shop.yogizogi_android.ui.view.auth.signup.process

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.addCallback
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentPasswordBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel

@AndroidEntryPoint
class PasswordFragment : BaseFragment<FragmentPasswordBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_password
) {
    override fun initView() {
        initBackBtn()
        watchPassword()
        btnPasswordCheck()
    }

    override fun initAfterBinding() {

    }

    private fun btnPasswordCheck(){
        binding.btnNext.setOnClickListener {
            if (viewModel.password.value.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_password),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (it.isActivated) {
                viewModel.stepUp()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun watchPassword() {
        binding.tilEtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val password = editable.toString()
                viewModel.updatePassword(password)
                if (password.isEmpty() || isPasswordValid(password)) {
                    binding.tilLayout.error = null
                    binding.btnNext.isActivated = true
                } else {
                    binding.tilLayout.error = resources.getString(R.string.signup_error_password)
                    binding.btnNext.isActivated = false
                }
            }
        })
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]).{7,}$")
        return regex.matches(password)
    }

    private fun initBackBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.stepDown()
        }
    }
}