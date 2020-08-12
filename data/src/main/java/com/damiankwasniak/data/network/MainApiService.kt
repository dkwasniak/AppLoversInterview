package com.damiankwasniak.data.network

import com.damiankwasniak.data.model.LoginApiModel
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApiService {

    @POST("login")
    fun login(@Body loginApiModel: LoginApiModel): Completable
}