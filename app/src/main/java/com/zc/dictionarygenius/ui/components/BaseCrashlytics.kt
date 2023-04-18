package com.zc.dictionarygenius.ui.components

interface BaseCrashlytics {
    fun reportNonFatalException(exception: Throwable)
}