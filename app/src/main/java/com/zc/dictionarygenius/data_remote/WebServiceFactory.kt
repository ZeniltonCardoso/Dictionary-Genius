package com.zc.dictionarygenius.data_remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit


object WebServiceFactory {

    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String = "",
        acceptLenient: Boolean = false
    ): T {
        val gsonFactory = if (acceptLenient) {
            val gson = GsonBuilder().setLenient().create()
            GsonConverterFactory.create(gson)
        } else {
            GsonConverterFactory.create()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(gsonFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .dispatcher(dispatcher())
            .connectTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .build()

    private fun dispatcher() = Dispatcher().run {
        maxRequests = 1
        maxRequestsPerHost = 1
        this
    }

    object UnitConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type, annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            return if (type == Unit::class.java) UnitConverter else null
        }

        private object UnitConverter : Converter<ResponseBody, Unit> {
            override fun convert(value: ResponseBody) {
                value.close()
            }
        }
    }
}


