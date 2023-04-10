package com.zc.dictionarygenius.data_remote.service

import com.zc.dictionarygenius.data.model.DictionaryResponse
import com.zc.dictionarygenius.data_remote.model.GenericResponse
import com.zc.dictionarygenius.data_remote.service.EnglishDictionaryService.SecurityContact.SECURITY_CONTACT_SEND_EMAIL
import com.zc.dictionarygenius.data_remote.service.EnglishDictionaryService.SecurityContact.WORK
import retrofit2.http.GET
import retrofit2.http.Path

interface EnglishDictionaryService {
    @GET(SECURITY_CONTACT_SEND_EMAIL)
    fun getEnglishWords(
        @Path(WORK) word: String
    ) : GenericResponse<List<DictionaryResponse>>

    private object SecurityContact {
        const val WORK = "word"
        const val SECURITY_CONTACT_SEND_EMAIL = "en/{$WORK}"
    }
}