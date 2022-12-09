package aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface Screen {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    object Products: Screen {

        override val name = "products"

        override val route = name
    }

    object Card: Screen {

        const val BUYER_ID = "buyerId"
        const val SELLER_ID = "sellerId"
        const val PRODUCT_ID = "snippetId"
        const val MAX_COUNT = "maxCount"

        override val name = "bankCard"

        override val route = "$name/{$BUYER_ID}/{$SELLER_ID}/{$PRODUCT_ID}/{$MAX_COUNT}"

        override val arguments = listOf(
            navArgument(name = BUYER_ID) { type = NavType.IntType },
            navArgument(name = SELLER_ID) { type = NavType.IntType },
            navArgument(name = PRODUCT_ID) { type = NavType.IntType },
            navArgument(name = MAX_COUNT) { type = NavType.IntType }
        )

        fun createRoute(buyerId: Int, sellerId: Int, productId: Int, maxCount: Int): String {
            return "$name/$buyerId/$sellerId/$productId/$maxCount"
        }
    }
}
