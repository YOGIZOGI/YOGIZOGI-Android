package org.shop.yogizogi_android.ui.view.main.home.creview

import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.FragmentCreateReviewBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class CreateReviewFragment : BaseFragment<FragmentCreateReviewBinding, CreateReviewViewModel>(
    CreateReviewViewModel::class.java,
    R.layout.fragment_create_review
) {
    override fun initView() {

    }

    override fun initAfterBinding() {

    }
}