package com.zc.dictionarygenius.ui.search_dictionary

import androidx.lifecycle.ViewModel
import com.zc.dictionarygenius.domain.model.DictionaryModel
import com.zc.dictionarygenius.domain.usecase.GetEnglishDictionaryUseCase
import com.zc.dictionarygenius.ui.components.mutableStateOf
import com.zc.dictionarygenius.ui.components.postError
import com.zc.dictionarygenius.ui.components.postNeutral
import com.zc.dictionarygenius.ui.components.postSuccess
import com.zc.dictionarygenius.ui.components.useCase
import org.koin.core.component.KoinComponent

class SearchDictionaryViewModel : ViewModel(), KoinComponent {
    val getEnglishDictionaryUseCase: GetEnglishDictionaryUseCase by useCase()
    private val _resultSearchState by mutableStateOf<List<DictionaryModel>>()
    val resultSearchState get() = _resultSearchState.value

    fun searchEnglish(searchInput: String) {
        getEnglishDictionaryUseCase.cancel()
        getEnglishDictionaryUseCase(
            params = GetEnglishDictionaryUseCase.Param(searchInput),
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