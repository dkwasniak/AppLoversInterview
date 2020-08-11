package com.damiankwasniak.data.di

import com.damiankwasniak.domain.repository.MainRepository
import com.damiankwasniak.domain.repository.PhotosRepository
import io.realm.Realm
import org.koin.dsl.module

val dataModule = module {

    single {
        AppPrefs(get())
    }

    single {
        AuthorizationRepositoryImpl(get()) as MainRepository
    }

    single {
        PhotosRepositoryImpl(get()) as PhotosRepository
    }

    single<Realm> {
        Realm.getDefaultInstance()
    }
}