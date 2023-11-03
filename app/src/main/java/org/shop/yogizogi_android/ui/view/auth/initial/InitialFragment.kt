package org.shop.yogizogi_android.ui.view.auth.initial

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentInitialBinding
import org.shop.yogizogi_android.ui.base.BaseFragment
import org.shop.yogizogi_android.ui.view.main.MainActivity

@AndroidEntryPoint
class InitialFragment : BaseFragment<FragmentInitialBinding, InitialViewModel>(
    InitialViewModel::class.java,
    R.layout.fragment_initial
) {
    override fun initView() {
        binding.tvTest.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initAfterBinding() {

    }
}