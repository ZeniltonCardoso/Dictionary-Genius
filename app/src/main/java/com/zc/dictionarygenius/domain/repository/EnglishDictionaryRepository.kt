package com.zc.dictionarygenius.domain.repository

import com.zc.dictionarygenius.domain.model.DictionaryModel
import kotlinx.coroutines.flow.Flow

interface EnglishDictionaryRepository {
    fun getEnglishWords(word: String): Flow<List<DictionaryModel>>
}