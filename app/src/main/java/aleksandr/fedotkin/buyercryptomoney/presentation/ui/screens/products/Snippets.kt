package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.products

import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.ProductViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@Composable
fun Snippets(
    viewModel: ProductViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    navController: NavController
) {

    val state = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        viewModel.loadProducts()
    }

    val snippets by viewModel.products.collectAsState()

    val buyer by viewModel.buyer.collectAsState(initial = get())

    LazyColumn(
        state = state,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        itemsIndexed(items = snippets) { index, product ->

            val enabledButton = product.quantity != 0 && buyer.amountOfMoney >= product.price

            Snippet(
                product = product,
                enabledButton = enabledButton
            ) {
                viewModel.navigate(navController = navController, productModel = product)
            }

            if (index < snippets.lastIndex) {
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
