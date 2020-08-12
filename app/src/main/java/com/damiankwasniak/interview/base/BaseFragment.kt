package com.damiankwasniak.interview.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.damiankwasniak.interview.extensions.exhaustive
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
        registerBaseCommandsObserver()
    }

    private fun registerViewStateObserver() {
        getViewModel().observeViewState {
            onViewStateChanged(it)
        }
    }

    private fun registerBaseCommandsObserver() {
        getViewModel().observeBaseViewState {
            onBaseViewStateChanged(it)
        }
    }


    open fun onBaseViewStateChanged(command: BaseViewModel.BaseViewCommand) {
        when (command) {
            is BaseViewModel.BaseViewCommand.ShowError -> showError(command.message)
        }.exhaustive
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

    open fun showError(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
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