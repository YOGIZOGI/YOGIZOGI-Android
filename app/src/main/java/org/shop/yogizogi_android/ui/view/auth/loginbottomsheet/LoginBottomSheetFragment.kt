package org.shop.yogizogi_android.ui.view.auth.loginbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.shop.yogizogi_android.databinding.FragmentLoginBottomsheetBinding
import org.shop.yogizogi_android.ui.view.auth.initial.InitialFragment

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
        isCancelable = false

        binding.ivBack.setOnClickListener {
            (parentFragment as? InitialFragment)?.resetInitialFragment()
            dismiss()
        }
    }
}