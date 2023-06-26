package com.zc.dictionarygenius.data_remote.service

import com.zc.dictionarygenius.data.model.CepResponse
import com.zc.dictionarygenius.data_remote.service.CepService.SecurityContact.CEP
import com.zc.dictionarygenius.data_remote.service.CepService.SecurityContact.SECURITY_CEP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {
    @GET(SECURITY_CEP)
    fun getCep(
        @Path(CEP) cep: String
    ) : Call<CepResponse>

    private object SecurityContact {
        const val CEP = "cep"
        const val SECURITY_CEP = "ws/{$CEP}/json"
    }
}