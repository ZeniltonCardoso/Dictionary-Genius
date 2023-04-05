package com.zc.dictionarygenius.ui.overview

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zc.dictionarygenius.data.Endpoint
import com.zc.dictionarygenius.data.NetworkUtils
import com.zc.dictionarygenius.data.model.DictionaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDictionaryViewModel : ViewModel() {

    private val _uiState = mutableStateOf(listOf<DictionaryResponse>())
    val uiState get() = _uiState.value

    fun getWordsEnglish(context: Context?, searchInput: String) {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.dictionaryapi.dev/api/v2/entries/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts(searchInput)

        callback.enqueue(object : Callback<List<DictionaryResponse>> {
            override fun onFailure(call: Call<List<DictionaryResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<DictionaryResponse>>,
                response: Response<List<DictionaryResponse>>
            ) {
                _uiState.value = response.body() ?: listOf()
            }
        })
    }

}