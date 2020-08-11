package com.damiankwasniak.data.di

import io.realm.Realm
import org.koin.dsl.module

val dataModule = module {


    single<Realm> {
        Realm.getDefaultInstance()
    }
}