package com.damiankwasniak.data.mapper

import com.damiankwasniak.data.model.LoginApiModel
import com.damiankwasniak.domain.model.LoginDomainModel

fun LoginApiModel.mapToDomainModel(): LoginDomainModel {
    return LoginDomainModel(
        login = this.login,
        password = this.password
    )
}

fun LoginDomainModel.mapToApiModel(): LoginApiModel {
    return LoginApiModel(
        login = this.login,
        password = this.password
    )
}