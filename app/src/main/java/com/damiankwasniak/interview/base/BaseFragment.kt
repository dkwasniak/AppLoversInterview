package com.damiankwasniak.interview.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger


abstract class BaseFragment<T : BaseViewModel<U>, U : Any> : Fragment(), OnBackPressedAwareFragment, AnkoLogger {

    val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun provideLayoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(provideLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewStateObserver()
    }

    private fun registerViewStateObserver() {
        getViewModel().observeViewState {
            onViewStateChanged(it)
        }
    }

    abstract fun getViewModel(): BaseViewModel<U>

    abstract fun onViewStateChanged(command: U)

    override fun onResume() {
        super.onResume()
        getViewModel().onResume()
    }

    override fun onDetach() {
        super.onDetach()
        getViewModel().dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    open fun close() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }
}

interface OnBackPressedAwareFragment {
    fun onBackPressed(): Boolean
}