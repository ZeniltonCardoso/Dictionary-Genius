package com.zc.dictionarygenius.data.repository

import com.zc.dictionarygenius.data.remote.CepRemoteDataSource
import com.zc.dictionarygenius.domain.model.CepModel
import com.zc.dictionarygenius.domain.repository.CepRepository
import kotlinx.coroutines.flow.Flow

class CepRepositoryImpl(
    private val remoteDataSource: CepRemoteDataSource
) : CepRepository {
    override fun getCep(cep: String): Flow<CepModel> =
        remoteDataSource.getCep(cep)
}