package com.damiankwasniak.interview.di

import com.damiankwasniak.domain.di.SchedulersProvider
import com.damiankwasniak.interview.provider.AndroidResourcesProvider
import com.damiankwasniak.interview.provider.ResourcesProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val appModule = module {

    single<ResourcesProvider> {
        AndroidResourcesProvider(get())
    }

    single<SchedulersProvider> {
        object : SchedulersProvider {
            override fun getIOScheduler(): Scheduler = Schedulers.io()

            override fun getMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
        }
    }

}