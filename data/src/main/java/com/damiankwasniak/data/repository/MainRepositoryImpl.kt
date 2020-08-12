package com.damiankwasniak.data.repository

import com.damiankwasniak.data.mapper.mapToApiModel
import com.damiankwasniak.data.network.MainApiService
import com.damiankwasniak.domain.model.LoginDomainModel
import com.damiankwasniak.domain.repository.MainRepository
import io.reactivex.Completable

class MainRepositoryImpl(
    private val mainApiService: MainApiService
) : MainRepository {


    override fun login(loginDomainModel: LoginDomainModel): Completable {
        return mainApiService.login(loginDomainModel.mapToApiModel())
    }


}