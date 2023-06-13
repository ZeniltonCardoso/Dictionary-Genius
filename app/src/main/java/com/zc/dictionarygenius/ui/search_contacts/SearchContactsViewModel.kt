package com.zc.dictionarygenius.ui.search_contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zc.dictionarygenius.UserLogin
import com.zc.dictionarygenius.domain.repository.Repository
import com.zc.dictionarygenius.ui.components.mutableStateOf
import com.zc.dictionarygenius.ui.components.postNeutral
import com.zc.dictionarygenius.ui.components.postSuccess
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchContactsViewModel : ViewModel(), KoinComponent {

    private val reference: Repository by inject()
    private val _resultLoginUser by mutableStateOf<UserLogin>()
    val resultLoginUser get() = _resultLoginUser.value

    fun searchContact(user: String) = viewModelScope.launch {
        reference.searchContact {
            _resultLoginUser.postSuccess(it)
        }
    }

    fun resetState() {
        _resultLoginUser.postNeutral()
    }
}