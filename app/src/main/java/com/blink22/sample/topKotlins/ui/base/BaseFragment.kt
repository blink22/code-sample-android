package com.blink22.sample.topKotlins.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.blink22.sample.topKotlins.BR
import com.blink22.sample.topKotlins.ui.utils.observe


abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val contentLayoutId: Int) :
    Fragment() {
    protected abstract val vm: BaseViewModel
    private var _vb: T? = null
    protected val vb get() = _vb!!


    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding?>(
            LayoutInflater.from(context), contentLayoutId, container, false
        ).apply {
            this@BaseFragment._vb = this as T
            setViewBinding(this)
            lifecycleOwner = viewLifecycleOwner
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        initViews()
    }

    open fun initViews() {/*
           The class that will extend BaseFragment can override this function
           and there is no specific or common implementation to be placed here
        */
    }

    open fun setViewBinding(vb: T) {
        vb.setVariable(BR.vm, vm)
    }

    open fun setupObservers() {
        observe(vm.baseEvent) {
            when (it) {
                BaseEvent.Back -> activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    protected fun updateStatusBarColor(color: Int) {
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(requireContext(), color)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    fun navigateTo(navDirections: NavDirections) {
        try {
            findNavController().navigate(navDirections)
        } catch (e: Exception) {
            // TODO: Handle exceptions
        }
    }
}