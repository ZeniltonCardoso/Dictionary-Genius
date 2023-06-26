package com.zc.dictionarygenius.data_remote.datasource

import android.util.Log
import com.zc.dictionarygenius.data.remote.CepRemoteDataSource
import com.zc.dictionarygenius.data_remote.mapper.CepMapper
import com.zc.dictionarygenius.data_remote.service.CepService
import com.zc.dictionarygenius.domain.model.CepModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class CepDataSourceImpl(
    private val webService: CepService
): CepRemoteDataSource, KoinComponent {
    override fun getCep(cep: String): Flow<CepModel> = flow {
        webService.getCep(cep).execute().body()?.let {
            Log.d("CepDataSourceImpl", "getCep: $it")
            CepMapper.toDomain(
                it
            )
        }?.let {
            Log.d("CepDataSourceImpl", "getCep: $it")
            emit(
                it
            )
        }
    }
}