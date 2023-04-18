package com.zc.dictionarygenius.ui.search_dictionary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zc.dictionarygenius.R
import com.zc.dictionarygenius.domain.model.DictionaryModel
import com.zc.dictionarygenius.ui.components.SearchBar
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private var dictionaryResponse by mutableStateOf(DictionaryModel())

@Composable
fun SearchDictionaryScreen(
    navHostController: NavHostController
) {
    val viewModel = koinViewModel<SearchDictionaryViewModel>()
    Handler(viewModel)
    BodyScreen(viewModel)
}

@Composable
fun Handler(viewModel: SearchDictionaryViewModel) {
    viewModel.resultSearchState.stateHandler(
        onSuccess = {
            dictionaryResponse = it.firstOrNull() ?: DictionaryModel()
        }
    )
}

@Composable
private fun BodyScreen(
    viewModel: SearchDictionaryViewModel
) {
    Column(
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
            .padding(horizontal = 16.dp)
    ) {
        Search(viewModel)
        BodyScreen(colors)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Search(
    viewModel: SearchDictionaryViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchInput by remember {
        mutableStateOf("")
    }
    SearchBar(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        value = searchInput,
        hint = stringResource(R.string.search_your_word_here),
        color = Color.Blue,
        onValueChanged = {
            searchInput = it
        },
        onIconCloseClick = {
            searchInput = ""
            keyboardController?.hide()
        }
    )
    LaunchedEffect(key1 = searchInput, block = {
        when {
            searchInput.length == 2 -> {
                delay(500)
                viewModel.searchEnglish(searchInput)
            }

            searchInput.length > 2 -> {
                delay(1_350)
                viewModel.searchEnglish(searchInput)
            }
        }
    })
}

@Composable
private fun BodyScreen(colors: Colors) {
    if (!dictionaryResponse.meanings.isNullOrEmpty()) {
        Spacer(Modifier.height(12.dp))
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
                    it.definitions?.let { definitions ->
                        val result = definitions.joinToString(separator = "\n") { example ->
                            example.example
                        }
                        if (result.isNotEmpty()) {
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Definitions: $result",
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

