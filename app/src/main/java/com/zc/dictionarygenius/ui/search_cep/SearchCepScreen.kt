package com.zc.dictionarygenius.ui.search_cep


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
import androidx.compose.material.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zc.dictionarygenius.domain.model.CepModel
import com.zc.dictionarygenius.ui.components.AlertDialog
import com.zc.dictionarygenius.ui.components.SearchCepText
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private var searchResponse by mutableStateOf(CepModel())
private var errorState by mutableStateOf(false)

@Composable
fun SearchCepScreen(
    navHostController: NavHostController
) {
    val viewModel = koinViewModel<SearchCepViewModel>()
    Handler(viewModel)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
            .padding(horizontal = 16.dp)
    ) {
        Search(viewModel)
        BodyScreen(colors, viewModel)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = {
                viewModel.resetState()
                navHostController.navigate("SearchDictionaryScreen")
            }
        ) {
            Text(text = "Fazer Login")
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Search(
    viewModel: SearchCepViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchInput by remember {
        mutableStateOf("")
    }
    SearchCepText(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        value = searchInput,
        hint = "Pesquisar Cep",
        onValueChanged = {
            searchInput = it
        },
        onIconCloseClick = {
            searchInput = ""

            viewModel.resetState()
            keyboardController?.hide()
        }
    )
    LaunchedEffect(key1 = searchInput, block = {
        when {
            searchInput.length == 2 -> {
                delay(500)
                viewModel.searchCep(searchInput)
            }

            searchInput.length > 2 -> {
                delay(1_350)
                viewModel.searchCep(searchInput)
            }
        }
    })
}

@Composable
private fun BodyScreen(colors: Colors, viewModel: SearchCepViewModel) {
    if (searchResponse.cep.isNotEmpty()) {
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    text = searchResponse.cep,
                    style = MaterialTheme.typography.body2.copy(
                        Color.White
                    )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    text = searchResponse.logradouro,
                    style = MaterialTheme.typography.body2.copy(
                        Color.White
                    )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    text = searchResponse.bairro,
                    style = MaterialTheme.typography.body2.copy(
                        Color.White
                    )
                )
            }
        }
    }
    if (errorState) {
        AlertDialog(
            onDismiss = {
                viewModel.resetState()
                errorState = false
            },
            bodyText = "Não foi encontrado",
            buttonText = "Ok"
        )
    }
}

@Composable
fun Handler(viewModel: SearchCepViewModel) {
    viewModel.resultSearchState.stateHandler(
        onSuccess = {
            searchResponse = it
        }
    )
}
