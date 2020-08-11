package com.damiankwasniak.domain.di

import com.damiankwasniak.domain.interactor.MainInteractor
import org.koin.dsl.module

val domainModule = module {

    single<MainInteractor> {
        MainInteractor(get())
    }

}