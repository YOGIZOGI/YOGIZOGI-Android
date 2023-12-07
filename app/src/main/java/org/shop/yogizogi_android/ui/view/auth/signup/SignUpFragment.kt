package org.shop.yogizogi_android.ui.view.auth.signup

import android.util.Log
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentSignUpBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.initial.loginbottomsheet.LoginBottomSheetFragment
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
        PhoneFragment(),
        VerifyFragment(),
        AgreeFragment(),
        PasswordFragment(),
        PasswordCheckFragment()
    )

    override fun initView() {
        with(binding) {
            sliderSignupProcess.value = viewModel.signUpStep.value.toFloat()
            sliderSignupProcess.isEnabled = false
        }

        initBackBtn()
    }

    override fun initAfterBinding() {
        observeStep()
    }

    private fun observeStep() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.signUpStep.collect { step ->
                Log.d("SignUpFragment step", step.toString())
                when (step) {
                    0 -> {
                        replaceFragment(step)
                        binding.sliderSignupProcess.value = step.toFloat()
                        binding.tvTextSelfVerify.text =
                            resources.getString(R.string.signup_verify_text)
                    }

                    1 -> {
                        replaceFragment(step)
                        binding.sliderSignupProcess.value = step.toFloat()
                        binding.tvTextSelfVerify.text =
                            resources.getString(R.string.signup_verify_text)
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

    private fun initBackBtn() {
        binding.btnBack.setOnClickListener {
            if (viewModel.signUpStep.value > 0) {
                viewModel.stepDown()
            } else {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }
}