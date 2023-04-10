package com.zc.dictionarygenius.di

import com.zc.dictionarygenius.ui.search_dictionary.SearchDictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SearchDictionaryViewModel() }
}
