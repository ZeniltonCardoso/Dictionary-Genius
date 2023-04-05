package com.zc.dictionarygenius.di

import com.zc.dictionarygenius.domain.ThreadContextProvider
import org.koin.dsl.module

val domainModule = module {

    single {
        ThreadContextProvider()
    }

}