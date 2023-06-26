package com.zc.dictionarygenius.domain.repository

import com.zc.dictionarygenius.domain.model.CepModel
import kotlinx.coroutines.flow.Flow

interface CepRepository {
    fun getCep(cep: String) : Flow<CepModel>
}