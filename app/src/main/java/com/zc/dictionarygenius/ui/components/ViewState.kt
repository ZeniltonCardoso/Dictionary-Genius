package com.zc.dictionarygenius.ui.components

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewState<T>(
    val status: Status = Status.NEUTRAL,
    val data: T? = null,
    val error: Throwable? = null
) : KoinComponent {

    private val baseCrashlytics: BaseCrashlytics by inject()

    fun stateHandler(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        loading: () -> Unit = {},
    ) {
        when (status) {
            Status.SUCCESS -> data?.let { onSuccess(it) } ?: throw RuntimeException()
            Status.ERROR -> {
                error?.let {
                    baseCrashlytics.reportNonFatalException(error)
                    onError(it)
                } ?: throw RuntimeException()
            }
            Status.LOADING -> loading()
            else -> Unit
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, NEUTRAL
    }
}

fun <T> ViewState<T>?.isSuccess() = this?.status?.equals(ViewState.Status.SUCCESS) ?: false
fun <T> ViewState<T>?.isError() = this?.status?.equals(ViewState.Status.ERROR) ?: false
fun <T> ViewState<T>?.isLoading() = this?.status?.equals(ViewState.Status.LOADING) ?: false
fun <T> ViewState<T>?.isNeutral() = this?.status?.equals(ViewState.Status.NEUTRAL) ?: false

