package org.shop.yogizogi_android.ui.view.auth.initial

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentInitialBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.auth.loginbottomsheet.LoginBottomSheetFragment
import org.shop.yogizogi_android.utils.toInvisible
import org.shop.yogizogi_android.utils.toVisible

@AndroidEntryPoint
class InitialFragment : BaseFragment<FragmentInitialBinding, InitialViewModel>(
    InitialViewModel::class.java,
    R.layout.fragment_initial
) {
    override fun initView() {
//        binding.tvTest.setOnClickListener {
//            val intent = Intent(requireActivity(), MainActivity::class.java)
//            startActivity(intent)
//        }
//        binding.tvTestSend.setOnClickListener {
//            viewModel.getVerifyCode("01013409584")
//        }
        binding.btnPhoneLogin.setOnClickListener {
            binding.groupLoginOptions.toInvisible()
            binding.groupDefaultBackgroundImage.toInvisible()
            binding.ivLoginLogo.toVisible()
            LoginBottomSheetFragment().show(childFragmentManager,"initialFragment")
        }
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.codeProcess.collect { result ->
                        when (result) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {

                            }
                            is Resource.Error -> {
                                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    fun resetInitialFragment(){
        with(binding){
            groupLoginOptions.toVisible()
            groupDefaultBackgroundImage.toVisible()
            ivLoginLogo.toInvisible()
        }
    }
}