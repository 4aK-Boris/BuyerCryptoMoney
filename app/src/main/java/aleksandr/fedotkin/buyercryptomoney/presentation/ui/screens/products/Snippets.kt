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
import org.koin.androidx.compose.koinViewModel

@Composable
fun Snippets(
    viewModel: ProductViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    navController: NavController
) {

    val state = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        viewModel.loadSnippets()
    }

    val snippets by viewModel.snippets.collectAsState()

    val buyer by viewModel.buyer.collectAsState()

    LazyColumn(
        state = state,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        itemsIndexed(items = snippets) { index, item ->

            val enabledButton = item.quantity != 0 && buyer.amountOfMoney >= item.price

            Snippet(
                snippet = item,
                viewModel = viewModel,
                enabledButton = enabledButton
            ) {
                viewModel.navigate(navController = navController, snippet = item)
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