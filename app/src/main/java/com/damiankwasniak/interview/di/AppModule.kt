package com.damiankwasniak.interview.di

import com.damiankwasniak.interview.provider.AndroidResourcesProvider
import com.damiankwasniak.interview.provider.ResourcesProvider
import org.koin.dsl.module

val appModule = module {

    single<ResourcesProvider> {
        AndroidResourcesProvider(get())
    }


}