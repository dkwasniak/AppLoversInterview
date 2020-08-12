package com.damiankwasniak.data.model

enum class ErrorCode(val code: Int) {

    UNKNOWN(666),

    // AUTH ERRORS
    BAD_REQUEST(400),
    UNPROCESSABLE_ENTITY(422),
    INTERNAL_SERVER_ERROR(500);


    companion object {
        fun fromId(code: Int?): ErrorCode {
            return values().firstOrNull {
                it.code == code
            } ?: UNKNOWN
        }

    }

}