package org.shop.yogizogi_android.ui.view.auth.signup.process

import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentPhoneBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel

@AndroidEntryPoint
class PhoneFragment : BaseFragment<FragmentPhoneBinding, SignUpViewModel>(
    SignUpViewModel::class.java,
    R.layout.fragment_phone
) {
    override fun initView() {
        binding.btnRequest.setOnClickListener {
            viewModel.stepUp()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }
    }

    override fun initAfterBinding() {

    }
}