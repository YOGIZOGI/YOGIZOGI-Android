package org.shop.yogizogi_android.ui.view.auth.signup.process

import android.content.Intent
import androidx.activity.addCallback
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
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
        binding.btnComplete.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            viewModel.stepDown()
        }
    }

    override fun initAfterBinding() {

    }
}