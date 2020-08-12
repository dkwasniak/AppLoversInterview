package com.damiankwasniak.data.di

import android.util.Log
import com.damiankwasniak.data.BuildConfig
import com.damiankwasniak.data.network.ErrorInterceptor
import com.damiankwasniak.data.network.MainApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val MAIN_RETROFIT = "main_retrofit"

const val TIMEOUT_LENGTH_IN_SECONDS: Long = 30

const val MAIN_OK_HTTP = "main_ok_http"

const val LOGGING_INTERCEPTOR = "logging_interceptor"

const val ERROR_INTERCEPTOR = "error_interceptor"


val apiModule = module {

    single(named(MAIN_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(get(named(MAIN_OK_HTTP)))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MainApiService::class.java) as MainApiService
    }

    //provide main okHttp client
    single(named(MAIN_OK_HTTP)) {
        val builder = OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT_LENGTH_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_LENGTH_IN_SECONDS, TimeUnit.SECONDS)
            addInterceptor(get(named(LOGGING_INTERCEPTOR)))
            addInterceptor(get(named(ERROR_INTERCEPTOR)))
        }.also {
            if (BuildConfig.BUILD_TYPE == "debug") {
                it.addInterceptor(OkHttpProfilerInterceptor())
            }
        }
        builder.build() as OkHttpClient
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor { message ->
            Log.i("NETWORK_LOG", message)
        }.apply {
            HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Interceptor>(named(ERROR_INTERCEPTOR)) { ErrorInterceptor(get()) }

    // GsonProvider
    single {
        GsonBuilder().create() as Gson
    }
}
