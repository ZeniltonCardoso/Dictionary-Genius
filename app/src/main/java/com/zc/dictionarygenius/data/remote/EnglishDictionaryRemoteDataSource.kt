package com.zc.dictionarygenius.data.remote

import com.zc.dictionarygenius.domain.model.DictionaryModel
import kotlinx.coroutines.flow.Flow

interface EnglishDictionaryRemoteDataSource {
    fun getEnglishWords(word: String): Flow<List<DictionaryModel>>
}