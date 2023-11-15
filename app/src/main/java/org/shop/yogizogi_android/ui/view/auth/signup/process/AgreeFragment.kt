package org.shop.yogizogi_android.ui.view.auth.signup.process

import androidx.activity.addCallback
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentAgreeBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel

@AndroidEntryPoint
class AgreeFragment : BaseFragment<FragmentAgreeBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_agree
) {
    override fun initView() {
//        binding.btnSignup.setOnClickListener {
//            viewModel.stepUp()
//        }
        initBackBtn()
    }

    override fun initAfterBinding() {

    }

    private fun initBackBtn(){
        requireActivity().onBackPressedDispatcher.addCallback(this){
            viewModel.stepDown()
        }
    }
}