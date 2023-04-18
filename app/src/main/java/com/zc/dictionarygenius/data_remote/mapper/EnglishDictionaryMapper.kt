package com.zc.dictionarygenius.data_remote.mapper

import com.zc.dictionarygenius.data.model.DictionaryResponse
import com.zc.dictionarygenius.domain.model.Definitions
import com.zc.dictionarygenius.domain.model.DictionaryModel
import com.zc.dictionarygenius.domain.model.License
import com.zc.dictionarygenius.domain.model.Meanings
import com.zc.dictionarygenius.domain.model.Phonetics

object EnglishDictionaryMapper : DataRemoteMapper<DictionaryResponse, DictionaryModel>() {

    override fun toDomain(data: DictionaryResponse) = DictionaryModel(
        word = data.word,
        phonetics = data.phonetics?.map {
            Phonetics(
                text = it.text,
                audio = it.audio,
                sourceUrl = it.sourceUrl,
                license = License(
                    it.license?.name ?: "",
                    it.license?.url ?: ""
                )
            )
        },
        meanings = data.meanings?.map {
            Meanings(
                it.partOfSpeech,
                it.definitions?.map { definitions ->
                    Definitions(
                        definitions.definition,
                        definitions.example
                    )
                },
                it.synonyms
            )
        },
        license = License(
            data.license?.name ?: "",
            data.license?.url ?: ""
        ),
        sourceUrls = data.sourceUrls
    )

    fun listToDomain(list: List<DictionaryResponse>?): List<DictionaryModel> {
        val listResponse = mutableListOf<DictionaryModel>()
        list?.forEach {
            listResponse.add(
                toDomain(it)
            )
        }
        return listResponse.toList()
    }
}