package com.zc.dictionarygenius.di

import com.zc.dictionarygenius.ui.contacts.ContactsViewModel
import com.zc.dictionarygenius.ui.login.LoginViewModel
import com.zc.dictionarygenius.ui.search_contacts.SearchContactsViewModel
import com.zc.dictionarygenius.ui.search_dictionary.SearchDictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::ContactsViewModel)
    viewModelOf(::SearchDictionaryViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SearchContactsViewModel)
}
