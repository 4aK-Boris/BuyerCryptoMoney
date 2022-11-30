package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.products

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Products(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomBar()
    }) { paddingValues ->
        Snippets(paddingValues = paddingValues, navController = navController)
    }
}