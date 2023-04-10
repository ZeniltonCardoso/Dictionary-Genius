package com.zc.dictionarygenius.data_remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.CertificatePinner
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.reflect.Type
import java.security.cert.Certificate
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

    fun provideOkHttpClient(
        wasDebugVersion: Boolean,
        certificate: Certificate? = null
    ): OkHttpClient =
        OkHttpClient.Builder()
            .dispatcher(dispatcher())
            .httpLoggingInterceptor(wasDebugVersion)
            .connectTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .apply {
                if (certificate == null) return@apply

                certificatePinner(certificatePinner())
            }
            .build()

    private fun OkHttpClient.Builder.httpLoggingInterceptor(wasDebugVersion: Boolean) =
        when (wasDebugVersion) {
            true -> {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(interceptor)
            }
            else -> this
        }

    private fun dispatcher() = Dispatcher().run {
        maxRequests = 1
        maxRequestsPerHost = 1
        this
    }

    private fun certificatePinner() = CertificatePinner.Builder().build()

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


