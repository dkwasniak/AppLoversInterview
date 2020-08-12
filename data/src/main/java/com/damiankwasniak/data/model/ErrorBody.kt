package com.damiankwasniak.data.model

import com.google.gson.annotations.SerializedName

data class ErrorBody(

    @SerializedName("error")
    val errorMessage: String

)