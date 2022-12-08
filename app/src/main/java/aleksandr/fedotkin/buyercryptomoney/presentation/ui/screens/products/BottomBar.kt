package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.products

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.ProductViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private const val HALF = 500

@Composable
fun BottomBar(viewModel: ProductViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.loadBuyers()
    }

    val buyers by viewModel.buyers.collectAsState()

    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val index by remember { derivedStateOf { state.firstVisibleItemIndex } }
    val offset by remember { derivedStateOf { state.firstVisibleItemScrollOffset } }

    LaunchedEffect(key1 = index) {
        viewModel.setBuyerIndex(buyerIndex = index)
    }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            state = state
        ) {
            items(buyers) {
                Buyer(buyer = it)

                if (!state.isScrollInProgress) {
                    if (offset <= HALF) {
                        coroutineScope.scrollBasic(state, left = true)
                    } else {
                        coroutineScope.scrollBasic(state)
                    }

                    if (offset > HALF) {
                        coroutineScope.scrollBasic(state)
                    } else {
                        coroutineScope.scrollBasic(state, left = true)
                    }
                }
            }
        }
    }
}

private fun CoroutineScope.scrollBasic(listState: LazyListState, left: Boolean = false) {
    launch {
        val pos = if (left) listState.firstVisibleItemIndex else listState.firstVisibleItemIndex + 1
        listState.animateScrollToItem(pos)
    }
}

@Composable
private fun Buyer(buyer: BuyerModel) {

    val width = LocalConfiguration.current.screenWidthDp.dp

    ConstraintLayout(
        modifier = Modifier.width(width = width)
    ) {

        val (image, nick, amount) = createRefs()

        CoilImage(
            imageModel = { buyer.imageUrl }, modifier = Modifier
                .size(size = 48.dp)
                .clip(shape = CircleShape)
                .constrainAs(ref = image) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    start.linkTo(anchor = parent.start, margin = 16.dp)
                },
            loading = {
                CircularProgressIndicator(color = Color.Black, strokeWidth = 2.dp)
            },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            )
        )

        NickNameText(text = buyer.nickName, modifier = Modifier.constrainAs(ref = nick) {
            top.linkTo(anchor = parent.top)
            bottom.linkTo(anchor = parent.bottom)
            start.linkTo(anchor = image.end, margin = 12.dp)
        })

        AmountText(amount = buyer.amountOfMoney, modifier = Modifier.constrainAs(ref = amount) {
            top.linkTo(anchor = parent.top)
            bottom.linkTo(anchor = parent.bottom)
            end.linkTo(anchor = parent.end, margin = 32.dp)
        })
    }
}


@Composable
private fun NickNameText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = FontFamily.Serif,
        fontSize = 18.sp,
        fontWeight = FontWeight.W600,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun AmountText(amount: Int, modifier: Modifier) {
    Text(
        text = "$amount P",
        modifier = modifier,
        fontFamily = FontFamily.Cursive,
        fontSize = 18.sp,
        fontWeight = FontWeight.W600,
        fontStyle = FontStyle.Italic
    )
}
