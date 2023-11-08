package org.shop.yogizogi_android.ui.view.auth.initial

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentInitialBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.MainActivity

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
}