package org.shop.yogizogi_android.ui.view.auth.loginbottomsheet

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentLoginBottomsheetBinding
import org.shop.yogizogi_android.ui.view.auth.initial.InitialFragment

@AndroidEntryPoint
class LoginBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLoginBottomsheetBinding
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

        initBackBtn()

        binding.tvSignup.setOnClickListener {
            navigateToInputNumFragment()
            dismiss()
        }
    }

    private fun navigateToInputNumFragment() {
        val parentFragment = parentFragment
        parentFragment?.findNavController()
            ?.navigate(R.id.action_initialFragment_to_InputNumFragment)
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
}