package com.zc.dictionarygenius.di

import androidx.room.Room
import com.zc.dictionarygenius.data.Database
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "user-database"
        ).apply {
            if (BuildConfig.DEBUG) return@apply
        }.fallbackToDestructiveMigration().build()
    }

    single { get<Database>().userDao() }
}