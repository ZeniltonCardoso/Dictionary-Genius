package com.zc.dictionarygenius.ui.search_cep

import androidx.lifecycle.ViewModel
import com.zc.dictionarygenius.domain.model.CepModel
import com.zc.dictionarygenius.domain.usecase.GetCepUseCase
import com.zc.dictionarygenius.ui.components.mutableStateOf
import com.zc.dictionarygenius.ui.components.postError
import com.zc.dictionarygenius.ui.components.postNeutral
import com.zc.dictionarygenius.ui.components.postSuccess
import com.zc.dictionarygenius.ui.components.useCase
import org.koin.core.component.KoinComponent

class SearchCepViewModel : ViewModel(), KoinComponent {

    val getCepUseCase: GetCepUseCase by useCase()
    private val _resultSearchState by mutableStateOf<CepModel>()
    val resultSearchState get() = _resultSearchState.value

    fun searchCep(cep: String) {
        getCepUseCase.cancel()
        getCepUseCase(
            params = GetCepUseCase.Param(cep),
            onSuccess = {
                _resultSearchState.postSuccess(it)
            },
            onError = {
                _resultSearchState.postError(it)
            }
        )
    }

    fun resetState() {
        _resultSearchState.postNeutral()
    }
}