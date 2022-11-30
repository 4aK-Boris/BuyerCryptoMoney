package aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation

import aleksandr.fedotkin.buyercryptomoney.ui.BankCard
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.products.Products
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Products.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.Products.route,
            arguments = Screen.Products.arguments
        ) {
            Products(navController = navController)
        }
        composable(
            route = Screen.BankCard.route,
            arguments = Screen.BankCard.arguments
        ) { backStackEntry ->
            val buyerId = backStackEntry.arguments?.getInt(Screen.BankCard.BUYER_ID)
            val sellerId = backStackEntry.arguments?.getInt(Screen.BankCard.SELLER_ID)
            val snippetId = backStackEntry.arguments?.getInt(Screen.BankCard.SNIPPET_ID)
            requireNotNull(buyerId) { "buyerId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(sellerId) { "sellerId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(snippetId) { "snippetId parameter wasn't found. Please make sure it's set!" }
            BankCard(
                navController = navController,
                buyerId = buyerId,
                sellerId = sellerId,
                snippetId = snippetId
            )
        }
    }
}