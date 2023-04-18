package com.zc.dictionarygenius.di

import com.zc.dictionarygenius.data.repository.EnglishDictionaryRepositoryImpl
import com.zc.dictionarygenius.domain.core.ThreadContextProvider
import com.zc.dictionarygenius.domain.repository.EnglishDictionaryRepository
import com.zc.dictionarygenius.domain.usecase.GetEnglishDictionaryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {

    single {
        ThreadContextProvider()
    }

    single {
        CoroutineScope(Dispatchers.IO)
    }

    factory { (scope: CoroutineScope) ->
        GetEnglishDictionaryUseCase(
            scope = scope,
            repository = get()
        )
    }

    single<EnglishDictionaryRepository> { EnglishDictionaryRepositoryImpl(get()) }

}