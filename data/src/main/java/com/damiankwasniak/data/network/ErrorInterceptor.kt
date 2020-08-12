package com.damiankwasniak.data.network

import com.damiankwasniak.EMPTY_STRING
import com.damiankwasniak.data.model.ErrorBody
import com.damiankwasniak.data.model.ErrorCode
import com.google.gson.*
import com.google.gson.stream.MalformedJsonException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset

class ErrorInterceptor(private val gson: Gson) : Interceptor {

    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response?

        try {
            response = chain.proceed(request)
            val bodyString = getBodyFromResponse(response)
            if (bodyString.isEmpty()) {
                return response
            }

            when (val code = response.code()) {
                in 300..Int.MAX_VALUE -> {
                    throw getJsonErrorResponse(bodyString)?.let { error ->
                        CommunicationException(code, ErrorCode.fromId(code), errorMessage = error.errorMessage)
                    } ?: CommunicationException(code, null, response.message())
                }
                else -> return response
            }
        } catch (e: Exception) {
            throw e
        }
    }

    private fun getJsonErrorResponse(body: String): ErrorBody? {
        return try {
            gson.fromJson(JsonParser().parse(body).asJsonObject, ErrorBody::class.java)
        } catch (e: MalformedJsonException) {
            null
        } catch (e: JsonParseException) {
            null
        } catch (e: JsonSyntaxException) {
            null
        } catch (e: JsonIOException) {
            null
        } catch (e: Exception) {
            throw e
        }
    }

    private fun getBodyFromResponse(response: Response): String {
        val responseBody = response.body()
        val source = responseBody?.source()

        source?.let {
            try {
                it.request(Long.MAX_VALUE)
                val buffer = it.buffer()
                val charset = UTF8

                return buffer.clone().readString(charset)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return EMPTY_STRING
    }
}