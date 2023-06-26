package com.zc.dictionarygenius.data_remote.mapper

import com.zc.dictionarygenius.data.model.CepResponse
import com.zc.dictionarygenius.domain.model.CepModel

object CepMapper : DataRemoteMapper<CepResponse, CepModel>() {
    override fun toDomain(data: CepResponse) = CepModel(
        cep = data.cep,
        logradouro = data.logradouro,
        complemento = data.complemento,
        bairro = data.bairro,
        localidade = data.localidade,
        uf = data.uf,
        ibge = data.ibge,
        gia = data.gia,
        ddd = data.ddd,
        siafi = data.siafi
    )
}