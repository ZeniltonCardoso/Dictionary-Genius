package com.zc.dictionarygenius.di

import com.zc.dictionarygenius.data.remote.EnglishDictionaryRemoteDataSource
import com.zc.dictionarygenius.data_remote.SERVICE_BASE_URL
import com.zc.dictionarygenius.data_remote.WebServiceFactory
import com.zc.dictionarygenius.data_remote.datasource.EnglishDictionaryDataSourceImpl
import com.zc.dictionarygenius.data_remote.service.EnglishDictionaryService
import org.koin.android.BuildConfig.DEBUG
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteModule = module {
    single {
        WebServiceFactory.provideOkHttpClient()
    }

    single {
        WebServiceFactory.createWebService(
            get(),
            url = SERVICE_BASE_URL
        ) as EnglishDictionaryService
    }

    single<EnglishDictionaryRemoteDataSource> { EnglishDictionaryDataSourceImpl(get()) }
}

