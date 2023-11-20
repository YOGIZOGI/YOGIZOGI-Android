package org.shop.yogizogi_android.ui.view.auth.loginbottomsheet

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentLoginBottomsheetBinding
import org.shop.yogizogi_android.ui.view.auth.initial.InitialFragment
import org.shop.yogizogi_android.ui.view.auth.signup.SignUpViewModel
import org.shop.yogizogi_android.ui.view.main.MainActivity

@AndroidEntryPoint
class LoginBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLoginBottomsheetBinding
    private val viewModel: SignUpViewModel by viewModels({ requireParentFragment() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false
        initBackBtn()
        initLoginBtn()
        initSignupBtn()
        watchPhoneNumber()
        watchPassword()
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.logInProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        viewModel.saveUserData(result.data)
                        Toast.makeText(
                            requireContext(),
                            resources.getString(R.string.login_complete),
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
    }

    private fun initSignupBtn() {
        binding.btnSignup.setOnClickListener {
            navigateToInputNumFragment()
            dismiss()
        }
    }

    private fun navigateToInputNumFragment() {
        val parentFragment = parentFragment
        parentFragment?.findNavController()
            ?.navigate(R.id.action_initialFragment_to_inputNumFragment)
    }

    private fun initBackBtn() {
        binding.ivBack.setOnClickListener {
            (parentFragment as? InitialFragment)?.resetInitialFragment()
            dismiss()
        }

        requireDialog().setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                // 뒤로가기 버튼 클릭 시 처리할 작업
                (parentFragment as? InitialFragment)?.resetInitialFragment()
                dismiss()
                true
            } else {
                false
            }
        }
    }

    private fun watchPhoneNumber() {
        binding.etLoginPhonenumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val phoneNumber = editable.toString()
                viewModel.updatePhoneNumber(phoneNumber)
                if (phoneNumber.isEmpty() || isPhoneNumberValid(phoneNumber)) {
                    binding.layoutPhoneInput.error = null
                    binding.btnLogin.isActivated = true
                } else {
                    binding.layoutPhoneInput.error =
                        resources.getString(R.string.signup_error_default)
                    binding.btnLogin.isActivated = false
                }
            }
        })
    }

    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        val phoneRegex = """^010\d{8}${'$'}""".toRegex()
        return phoneRegex.matches(phoneNumber)
    }

    private fun watchPassword() {
        binding.etLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val password = editable.toString()
                viewModel.updatePassword(password)
                if (password.isEmpty()) {
                    binding.layoutPasswordInput.error =
                        resources.getString(R.string.signup_error_password)
                } else {
                    binding.layoutPasswordInput.error = null
                }
            }
        })
    }
}