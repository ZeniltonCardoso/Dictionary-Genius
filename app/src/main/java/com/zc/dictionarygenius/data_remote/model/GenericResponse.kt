package com.zc.dictionarygenius.data_remote.model

import com.google.gson.annotations.SerializedName

/**
 * Usage example: `GenericResponse<BasicDataResponse>`
 *
 * @param data is success object
 */
data class GenericResponse<T>(
    @SerializedName(value = "result", alternate = ["Result"]) val data: T?,
    @SerializedName("errors", alternate = ["Errors"]) val errors: List<String>?,
    @SerializedName("success", alternate = ["Success"]) val success: Boolean = false
)