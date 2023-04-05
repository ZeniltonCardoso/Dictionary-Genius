package com.zc.dictionarygenius

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import com.zc.dictionarygenius.di.dataModule
import com.zc.dictionarygenius.di.databaseModule
import com.zc.dictionarygenius.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        startKoin {
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    databaseModule
                )
            ).androidContext(applicationContext)
        }
    }
}