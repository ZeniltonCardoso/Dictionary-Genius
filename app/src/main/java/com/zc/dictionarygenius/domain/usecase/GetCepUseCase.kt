package com.zc.dictionarygenius.domain.usecase

import com.zc.dictionarygenius.domain.core.UseCase
import com.zc.dictionarygenius.domain.exception.MissingParamsException
import com.zc.dictionarygenius.domain.model.CepModel
import com.zc.dictionarygenius.domain.repository.CepRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetCepUseCase(
    scope: CoroutineScope,
    private val repository: CepRepository
) : UseCase<CepModel, GetCepUseCase.Param>(scope) {
    override fun run(params: Param?): Flow<CepModel> = when (params) {
        null -> throw MissingParamsException()
        else -> repository.getCep(params.cep)
    }

    data class Param(
        val cep: String
    )
}