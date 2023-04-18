package com.zc.dictionarygenius.data_remote.datasource

import com.zc.dictionarygenius.data.remote.EnglishDictionaryRemoteDataSource
import com.zc.dictionarygenius.data_remote.mapper.EnglishDictionaryMapper
import com.zc.dictionarygenius.data_remote.service.EnglishDictionaryService
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class EnglishDictionaryDataSourceImpl(
    private val webService: EnglishDictionaryService
) : EnglishDictionaryRemoteDataSource, KoinComponent {

    override fun getEnglishWords(word: String) = flow {
        emit(
            EnglishDictionaryMapper.listToDomain(
                webService.getEnglishWords(word).execute().body()
            )
        )
    }
}
