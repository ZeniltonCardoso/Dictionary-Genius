package com.zc.dictionarygenius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zc.dictionarygenius.ui.contacts.ContactsScreen
import com.zc.dictionarygenius.ui.login.LoginScreen
import com.zc.dictionarygenius.ui.search_cep.SearchCepScreen
import com.zc.dictionarygenius.ui.search_contacts.SearchContactsScreen
import com.zc.dictionarygenius.ui.search_dictionary.SearchDictionaryScreen
import com.zc.dictionarygenius.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryGeniusApp()
        }
    }
}

@Composable
fun DictionaryGeniusApp() {
    Theme {
        val navController = rememberNavController()
        Column {
            NavHost(navController = navController, startDestination = "LoginScreen") {
                composable("SearchDictionaryScreen") { SearchDictionaryScreen(navController) }
                composable("LoginScreen") { LoginScreen(navController) }
                composable("ContactsScreen") { ContactsScreen(navController) }
                composable("SearchContactsScreen") { SearchContactsScreen(navController) }
                composable("SearchCepScreen") { SearchCepScreen(navController) }
            }
        }
    }
}