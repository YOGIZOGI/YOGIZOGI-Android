package org.shop.yogizogi_android.ui.view.auth.signup.process

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
        binding.btnNext.setOnClickListener {
            viewModel.stepUp()
        }
    }

    override fun initAfterBinding() {

    }
}