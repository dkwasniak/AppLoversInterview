package com.damiankwasniak.domain.repository

import com.damiankwasniak.domain.model.LoginDomainModel
import io.reactivex.Completable


interface MainRepository {

    fun login(loginDomainModel: LoginDomainModel): Completable

}