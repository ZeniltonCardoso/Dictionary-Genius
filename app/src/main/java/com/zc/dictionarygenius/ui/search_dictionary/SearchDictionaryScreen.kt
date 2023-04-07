package com.zc.dictionarygenius.ui.search_dictionary

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zc.dictionarygenius.R
import com.zc.dictionarygenius.data.model.DictionaryResponse
import com.zc.dictionarygenius.ui.components.SearchBar

private var searchInput by mutableStateOf("")
private var dictionaryResponse by mutableStateOf(DictionaryResponse())

@SuppressLint("SuspiciousIndentation")
@Composable
fun SearchDictionaryScreen(
    navHostController: NavHostController,
    viewModel: SearchDictionaryViewModel = viewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
            .padding(horizontal = 16.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            value = searchInput,
            hint = stringResource(R.string.search_your_word_here),
            color = Color.Blue,
            onValueChanged = {
                searchInput = it
                if (searchInput.length >= 2)
                    viewModel.getWordsEnglish(context, searchInput)
            },
            onIconCloseClick = {
                searchInput = ""
            }
        )
        viewModel.uiState.map {
            dictionaryResponse = it
        }
        if (searchInput.length >= 2) {
            Spacer(Modifier.height(DefaultPadding))
            Card(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(size = 8.dp),
                border = BorderStroke(
                    1.5f.dp,
                    colors.primary
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    dictionaryResponse.meanings?.map {
                        val joinToString = it.synonyms.joinToString(", ")
                        if (it.synonyms.isNotEmpty()) Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center,
                            text = "Synonyms: $joinToString.",
                            style = MaterialTheme.typography.body2.copy(
                                Color.White
                            )
                        )
                        it.definitions?.map { text ->
                            if (text.example.isNotEmpty())
                                Text(
                                    modifier = Modifier
                                        .padding(bottom = 8.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    text = "Definitions: ${text.example}",
                                    style = MaterialTheme.typography.body2.copy(
                                        Color.White
                                    )
                                )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    val navController = rememberNavController()
    SearchDictionaryScreen(navController)
}

private val DefaultPadding = 12.dp