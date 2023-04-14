package com.zc.dictionarygenius.ui.search_dictionary

import androidx.lifecycle.ViewModel
import com.zc.dictionarygenius.domain.model.DictionaryModel
import com.zc.dictionarygenius.domain.usecase.GetEnglishDictionaryUseCase
import com.zc.dictionarygenius.ui.components.mutableStateOf
import com.zc.dictionarygenius.ui.components.postError
import com.zc.dictionarygenius.ui.components.postSuccess
import com.zc.dictionarygenius.ui.components.useCase
import org.koin.core.component.KoinComponent

class SearchDictionaryViewModel : ViewModel(), KoinComponent {
    val getEnglishDictionaryUseCase: GetEnglishDictionaryUseCase by useCase()
    private val _uiState by mutableStateOf<List<DictionaryModel>>()
    val uiState get() = _uiState.value

    fun getTermsFromAPi(searchInput: String) {
        getEnglishDictionaryUseCase(
            params = GetEnglishDictionaryUseCase.Param(searchInput),
            onSuccess = {
                _uiState.postSuccess(it)
            },
            onError = {
                _uiState.postError(it)
            }
        )
    }
}