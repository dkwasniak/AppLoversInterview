package com.damiankwasniak.data.di

import com.damiankwasniak.data.repository.MainRepositoryImpl
import com.damiankwasniak.domain.repository.MainRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<MainRepository> {
        MainRepositoryImpl(get(named(MAIN_RETROFIT)))
    }

}