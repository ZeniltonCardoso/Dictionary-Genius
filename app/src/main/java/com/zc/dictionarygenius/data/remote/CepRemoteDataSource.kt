package com.zc.dictionarygenius.data.remote

import com.zc.dictionarygenius.domain.model.CepModel
import kotlinx.coroutines.flow.Flow

interface CepRemoteDataSource {
    fun getCep(cep: String): Flow<CepModel>
}