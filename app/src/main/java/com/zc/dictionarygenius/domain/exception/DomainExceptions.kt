package com.zc.dictionarygenius.domain.exception


open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))

sealed class ParamException(message: String, title: String? = null) :
    DomainException(message, title)


class MissingParamsException : ParamException("Params must not be null")

open class DataSourceException(
    message: String? = null,
    cause: Throwable? = null,
    val code: String? = null,
    val header: Map<String, List<String>>? = null
) : Exception(message, cause) {

    fun itIsInMaintenance(): Boolean {
        return try {
            val code503 = (code?.toInt() ?: 0) == 503
            val key = "manutencao"
            code503 && header?.get(key)?.contains("true") == true
        } catch (e: Exception) {
            false
        }
    }

    fun isError500(): Boolean {
        return try {
            (code?.toInt() ?: 0) >= 500 && (code?.toInt() ?: 0) < 600
        } catch (e: Exception) {
            false
        }
    }
}
