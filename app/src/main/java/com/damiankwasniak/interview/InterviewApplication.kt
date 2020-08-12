package com.damiankwasniak.interview

import android.app.Application
import com.damiankwasniak.data.di.apiModule
import com.damiankwasniak.data.di.dataModule
import com.damiankwasniak.domain.di.domainModule
import com.damiankwasniak.interview.di.appModule
import com.damiankwasniak.interview.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InterviewApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val context = this
        startKoin {
            androidContext(context)
            modules(
                appModule,
                viewModelModule,
                dataModule,
                domainModule,
                apiModule
            )
        }
    }
}

