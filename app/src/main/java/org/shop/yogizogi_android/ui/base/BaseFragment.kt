package org.shop.yogizogi_android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import org.shop.yogizogi_android.utils.toGone
import org.shop.yogizogi_android.utils.toVisible

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel>(
    private val viewModelClass: Class<R>,
    @LayoutRes private val layoutResourceId: Int,
) : Fragment() {

    private var _binding: T? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = _binding!!

    lateinit var viewModel: R

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate<T>(inflater, layoutResourceId, container, false).apply {
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBeforeBinding()
        initView()
        initAfterBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBeforeBinding() {
        viewModel = ViewModelProvider(requireActivity())[viewModelClass]
    }

    /**
     * initiate view and click event
     */
    abstract fun initView()

    /**
     * initiate others (ex. observe Livedata)
     */
    abstract fun initAfterBinding()

    fun playAnimation(view: LottieAnimationView) {
        view.toVisible()
        view.playAnimation()
    }

    fun stopAnimation(view: LottieAnimationView) {
        view.pauseAnimation()
        view.toGone()
    }

    fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}