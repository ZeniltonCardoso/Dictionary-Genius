package com.zc.dictionarygenius.data_remote.datasource

import com.zc.dictionarygenius.data.remote.EnglishDictionaryRemoteDataSource
import com.zc.dictionarygenius.data_remote.RequestWrapper
import com.zc.dictionarygenius.data_remote.mapper.EnglishDictionaryMapper
import com.zc.dictionarygenius.data_remote.service.EnglishDictionaryService
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EnglishDictionaryDataSourceImpl(
    private val webService: EnglishDictionaryService
) : EnglishDictionaryRemoteDataSource, KoinComponent {
    private val requestWrapper: RequestWrapper by inject()

    override fun getEnglishWords(word: String) = flow {
        emit(
            EnglishDictionaryMapper.listToDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getEnglishWords(word)
                }
            )
        )
    }
}
