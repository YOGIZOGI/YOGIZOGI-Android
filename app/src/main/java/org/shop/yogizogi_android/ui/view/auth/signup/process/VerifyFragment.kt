package org.shop.yogizogi_android.ui.view.auth.signup.process

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentVerifyBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel

@AndroidEntryPoint
class VerifyFragment : BaseFragment<FragmentVerifyBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_verify
) {
    override fun initView() {
        binding.btnNext.setOnClickListener {
            viewModel.stepUp()
        }
    }

    override fun initAfterBinding() {

    }
}