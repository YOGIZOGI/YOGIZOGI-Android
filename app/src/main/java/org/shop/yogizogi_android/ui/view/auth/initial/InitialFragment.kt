package org.shop.yogizogi_android.ui.view.auth.initial

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentInitialBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.initial.loginbottomsheet.LoginBottomSheetFragment
import org.shop.yogizogi_android.utils.toInvisible
import org.shop.yogizogi_android.utils.toVisible

@AndroidEntryPoint
class InitialFragment : BaseFragment<FragmentInitialBinding, InitialViewModel>(
    InitialViewModel::class.java,
    R.layout.fragment_initial
) {
    override fun initView() {
        binding.btnPhoneLogin.setOnClickListener {
            binding.groupLoginOptions.toInvisible()
            binding.groupDefaultBackgroundImage.toInvisible()
            binding.ivLoginLogo.toVisible()
            LoginBottomSheetFragment().show(childFragmentManager, "initialFragment")
        }
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {

    }

    fun resetInitialFragment() {
        with(binding) {
            groupLoginOptions.toVisible()
            groupDefaultBackgroundImage.toVisible()
            ivLoginLogo.toInvisible()
        }
    }
}