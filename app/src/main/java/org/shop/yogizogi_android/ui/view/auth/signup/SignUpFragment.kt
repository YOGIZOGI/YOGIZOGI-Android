package org.shop.yogizogi_android.ui.view.auth.signup

import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentSignUpBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.process.AgreeFragment
import org.shop.yogizogi_android.ui.view.auth.signup.process.PasswordCheckFragment
import org.shop.yogizogi_android.ui.view.auth.signup.process.PasswordFragment
import org.shop.yogizogi_android.ui.view.auth.signup.process.PhoneFragment
import org.shop.yogizogi_android.ui.view.auth.signup.process.VerifyFragment

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_sign_up
) {
    private val fragmentList = listOf(
        PhoneFragment(), VerifyFragment(), AgreeFragment(), PasswordFragment(), PasswordCheckFragment()
    )

    override fun initView() {
        with(binding) {
            sliderSignupProcess.value = viewModel.signUpStep.value.toFloat()
            sliderSignupProcess.isEnabled = false
        }
    }

    override fun initAfterBinding() {
        observeStep()
    }

    private fun observeStep() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.signUpStep.collect { step ->
                when (step) {
                    0 -> {
                        replaceFragment(step)
                        binding.sliderSignupProcess.value = step.toFloat()
                    }

                    1 -> {
                        replaceFragment(step)
                        binding.sliderSignupProcess.value = step.toFloat()
                    }

                    2 -> {
                        replaceFragment(step)
                        binding.sliderSignupProcess.value = step.toFloat()
                        binding.tvTextSelfVerify.text = resources.getString(R.string.signup_agree)
                    }

                    3 -> {
                        replaceFragment(step)
                        binding.sliderSignupProcess.value = step.toFloat()
                        binding.tvTextSelfVerify.text = resources.getString(R.string.signup)
                    }
                    4 -> {
                        replaceFragment(step)
                    }
                }
            }
        }
    }

    private fun replaceFragment(step: Int) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_signup, fragmentList[step])
            .addToBackStack(null)
            .commit()
    }
}