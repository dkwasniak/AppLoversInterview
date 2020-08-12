package com.damiankwasniak.data.network

import com.damiankwasniak.data.model.ErrorCode
import java.io.IOException


class CommunicationException(
        val responseCode: Int,
        val errorCode: ErrorCode?,
        val errorMessage: String,
        override val message: String? = null) : IOException(message)