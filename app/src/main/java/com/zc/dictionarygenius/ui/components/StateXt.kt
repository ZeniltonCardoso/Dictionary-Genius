package com.zc.dictionarygenius.ui.components

import androidx.compose.runtime.MutableState

fun <T> MutableState<ViewState<T>>.postNeutral() {
    value = ViewState(ViewState.Status.NEUTRAL, null, null)
}

fun <T> MutableState<ViewState<T>>.postSuccess(data: T) {
    value = (ViewState(ViewState.Status.SUCCESS, data, null))
}

fun <T> MutableState<ViewState<T>>.postError(error: Throwable?) {
    value = (ViewState(ViewState.Status.ERROR, null, error))
}

fun <T> MutableState<ViewState<T>>.postError(message: String?) {
    value = (ViewState(ViewState.Status.ERROR, null, RuntimeException(message)))
}

fun <T> MutableState<ViewState<T>>.postLoading() {
    value = ViewState(ViewState.Status.LOADING, null, null)
}