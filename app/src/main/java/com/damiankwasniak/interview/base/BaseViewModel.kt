package com.damiankwasniak.interview.base

import androidx.lifecycle.ViewModel
import com.damiankwasniak.data.network.CommunicationException
import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.provider.ResourcesProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<T : Any>(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val disposables: CompositeDisposable = CompositeDisposable()

    var viewStateObserver: ((event: T) -> Unit)? = null

    var baseViewStateObserver: ((command: BaseViewCommand) -> Unit)? = null

    open fun dispose() {
        disposables.clear()
    }

    fun observeViewState(onNextEvent: (event: T) -> Unit) {
        viewStateObserver = onNextEvent
    }

    open fun onResume() {

    }

    open fun onPause() {

    }

    fun observeBaseViewState(onNextEvent: (event: BaseViewCommand) -> Unit) {
        this.baseViewStateObserver = onNextEvent
    }

    fun applyCommand(command: T) {
        this.viewStateObserver?.invoke(command)
    }

    fun T.apply() = viewStateObserver?.invoke(this)

    fun applyCommands(vararg commands: T) {
        commands.forEach {
            this.viewStateObserver?.invoke(it)
        }
    }

    open fun showError(throwable: Throwable) {
        val command = if (throwable is CommunicationException) {
            BaseViewCommand.ShowError(throwable.errorMessage)
        } else {
            BaseViewCommand.ShowError(resourcesProvider.getString(R.string.error_unknown))
        }
        this.baseViewStateObserver?.invoke(command)
    }

    protected fun string(resId: Int) = resourcesProvider.getString(resId)

    sealed class BaseViewCommand {
        class ShowError(val message: String) : BaseViewCommand()
    }
}

