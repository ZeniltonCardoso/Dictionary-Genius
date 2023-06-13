package com.zc.dictionarygenius.ui.contacts


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zc.dictionarygenius.ui.components.AlertDialog
import org.koin.androidx.compose.koinViewModel

private var successState by mutableStateOf(false)
private var errorState by mutableStateOf(false)

@Composable
fun ContactsScreen(
    navHostController: NavHostController
) {
    val viewModel = koinViewModel<ContactsViewModel>()
    var userValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    Handler(viewModel, navHostController)
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.weight(1f))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userValue,
            onValueChange = {
                userValue = it
            },
            label = { Text("Nome") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text("Telefone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
            onClick = {
                if (userValue.isNotEmpty() and passwordValue.isNotEmpty()) viewModel.saveUser(userValue, passwordValue)
                else println("Preencha os campos")
            }
        ) {
            Text(text = "Gravar Contato")
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
            onClick = {
                navHostController.navigate("SearchContactsScreen")
            }
        ) {
            Text(text = "Continuar")
        }
    }

    if (successState) {
        AlertDialog(
            onDismiss = {
                viewModel.resetState()
                successState = false
            },
            bodyText = "Gravado com sucesso",
            buttonText = "Ok"
        )
    }
    if (errorState) {
        AlertDialog(
            onDismiss = {
                viewModel.resetState()
                errorState = false
            },
            bodyText = "Falha na gravação, tente novamente",
            buttonText = "Ok"
        )
    }
}

@Composable
fun Handler(viewModel: ContactsViewModel, navHostController: NavHostController) {
    viewModel.resultLoginUser.stateHandler(
        onSuccess = {
            if (it) {
                successState = true
            } else {
                errorState = true
            }
        }
    )
}
