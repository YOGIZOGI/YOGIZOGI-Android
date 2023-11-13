package org.shop.yogizogi_android.ui.view.auth.signup

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentSignUpBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_sign_up
) {
    override fun initView() {
        binding.sliderSignupProcess.value = 2.0f
        binding.sliderSignupProcess.isEnabled = false
    }

    override fun initAfterBinding() {

    }
}