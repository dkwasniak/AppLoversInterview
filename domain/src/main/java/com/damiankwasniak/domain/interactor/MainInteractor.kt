package com.damiankwasniak.domain.interactor

import com.damiankwasniak.domain.di.SchedulersProvider
import com.damiankwasniak.domain.extension.applyCompletableIoSchedulers
import com.damiankwasniak.domain.model.LoginDomainModel
import com.damiankwasniak.domain.repository.MainRepository
import io.reactivex.Completable

class MainInteractor(
    private val schedulersProvider: SchedulersProvider,
    private val mainRepository: MainRepository
) {
    fun performLogin(login: String, password: String): Completable {
        return mainRepository.login(LoginDomainModel(login, password))
            .compose(applyCompletableIoSchedulers(schedulersProvider))
    }

}