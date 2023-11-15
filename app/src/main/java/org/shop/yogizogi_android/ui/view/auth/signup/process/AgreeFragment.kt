package org.shop.yogizogi_android.ui.view.auth.signup.process

import android.widget.Toast
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
        initCheck()
        initBackBtn()
        setNextBtn()
    }

    override fun initAfterBinding() {

    }

    private fun initCheck() {
        val allCheckList =
            listOf(binding.cbAgreePersonal, binding.cbAgreeLocation, binding.cbAgreeMarketing)

        binding.cbAll.setOnClickListener {
            if (binding.cbAll.isChecked) {
                allCheckList.forEach { checkbox ->
                    checkbox.isChecked = true
                }
            } else {
                allCheckList.forEach { checkbox ->
                    checkbox.isChecked = false
                }
            }
        }

        allCheckList.forEach { checkbox ->
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    binding.cbAll.isChecked = false
                } else {
                    binding.cbAll.isChecked = allCheckList.all { it.isChecked }
                }
            }
        }

        binding.btnSignup.isActivated =
            binding.cbAgreePersonal.isChecked && binding.cbAgreeLocation.isChecked
    }

    private fun setNextBtn() {
        binding.btnSignup.setOnClickListener {
            if (it.isActivated) {
                viewModel.stepUp()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signup_error_agree),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initBackBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.stepDown()
        }
    }
}