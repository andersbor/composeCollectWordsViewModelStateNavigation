package com.example.collectwordsviewmodelstatenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.collectwordsviewmodelstatenavigation.screens.Home
import com.example.collectwordsviewmodelstatenavigation.screens.Show
import com.example.collectwordsviewmodelstatenavigation.ui.theme.CollectWordsViewModelStateNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollectWordsViewModelStateNavigationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: WordsViewModelState = viewModel()
    // add to gradle file
    // https://developer.android.com/develop/ui/compose/libraries#viewmodel

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            Home(
                words = viewModel.words,
                onAddWord = { word -> viewModel.add(word) },
                onRemoveWord = { word -> viewModel.remove(word) },
                onClearList = { viewModel.clear() },
                onShowList = { navController.navigate(NavRoutes.Show.route) }
            )
        }

        composable(
            NavRoutes.Show.route
            // animations https://developer.android.com/develop/ui/compose/animation/composables-modifiers#animatedcontent
        ) {
            Show(
                words = viewModel.words,
                onNavigateBack = { navController.popBackStack() },
                onDeleteWord = { word -> viewModel.remove(word) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CollectWordsViewModelStateNavigationTheme {
        MainScreen()
    }
}