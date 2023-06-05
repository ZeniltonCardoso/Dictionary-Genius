package com.zc.dictionarygenius.domain.usecase

import com.zc.dictionarygenius.domain.core.UseCase
import com.zc.dictionarygenius.domain.exception.MissingParamsException
import com.zc.dictionarygenius.domain.model.DictionaryModel
import com.zc.dictionarygenius.domain.repository.EnglishDictionaryRepository
import kotlinx.coroutines.CoroutineScope

class GetEnglishDictionaryUseCase(
    scope: CoroutineScope,
    private val repository: EnglishDictionaryRepository
) : UseCase<List<DictionaryModel>, GetEnglishDictionaryUseCase.Param>(scope) {
    override fun run(params: Param?) = when (params) {
        null -> throw MissingParamsException()
        else -> repository.getEnglishWords(params.words)
    }

    data class Param(
        val words: String
    )
}