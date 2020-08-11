package com.damiankwasniak.interview.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger


abstract class BaseActivity<T : BaseViewModel<U>, U : Any> : AppCompatActivity(), LifecycleOwner, AnkoLogger {


    val disposables: CompositeDisposable = CompositeDisposable()

    val lifecycleRegistry: LifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    abstract fun provideLayoutRes(): Int

    abstract fun getViewModel(): BaseViewModel<U>

    abstract fun onViewStateChanged(command: U)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutRes())
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        registerViewStateObserver()
    }

    private fun registerViewStateObserver() {
        getViewModel().observeViewState {
            onViewStateChanged(it)
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    override fun onResume() {
        super.onResume()
        getViewModel().onResume()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        getViewModel().dispose()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }
}
