package com.zc.dictionarygenius.data.repository

import com.zc.dictionarygenius.data.remote.EnglishDictionaryRemoteDataSource
import com.zc.dictionarygenius.domain.model.DictionaryModel
import com.zc.dictionarygenius.domain.repository.EnglishDictionaryRepository
import kotlinx.coroutines.flow.Flow

class EnglishDictionaryRepositoryImpl(
    private val remoteDataSource: EnglishDictionaryRemoteDataSource
) : EnglishDictionaryRepository {
    override fun getEnglishWords(word: String): Flow<List<DictionaryModel>> =
        remoteDataSource.getEnglishWords(word)
}