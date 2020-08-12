package com.damiankwasniak.data.model

import com.google.gson.annotations.SerializedName

data class LoginApiModel(

    @SerializedName("username")
    val login: String,

    @SerializedName("password")
    val password: String
)