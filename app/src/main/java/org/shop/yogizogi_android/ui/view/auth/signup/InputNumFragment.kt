package org.shop.yogizogi_android.ui.view.auth.signup

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentInputNumBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class InputNumFragment : BaseFragment<FragmentInputNumBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_input_num
) {
    override fun initView() {

    }

    override fun initAfterBinding() {

    }
}