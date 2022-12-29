package aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.card.Card
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.code.CodeScreen
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.products.Products
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.json.Json

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
            route = Screen.Card.route,
            arguments = Screen.Card.arguments
        ) { backStackEntry ->
            val buyerId = backStackEntry.arguments?.getInt(Screen.Card.BUYER_ID)
            val sellerId = backStackEntry.arguments?.getInt(Screen.Card.SELLER_ID)
            val productId = backStackEntry.arguments?.getInt(Screen.Card.PRODUCT_ID)
            val maxCount = backStackEntry.arguments?.getInt(Screen.Card.MAX_COUNT)
            requireNotNull(buyerId) { "buyerId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(sellerId) { "sellerId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(productId) { "productId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(maxCount) { "maxCount parameter wasn't found. Please make sure it's set!" }
            Card(
                navController = navController,
                buyerId = buyerId,
                sellerId = sellerId,
                productId = productId,
                maxCount = maxCount
            )
        }
        composable(
            route = Screen.Code.route, arguments = Screen.Code.arguments
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString(Screen.Code.DATA)
            requireNotNull(json) { "json parameter wasn't found. Please make sure it's set!" }
            CodeScreen(
                navController = navController,
                data = Json.decodeFromString(string = json, deserializer = BuyModel.serializer())
            )
        }
    }
}
