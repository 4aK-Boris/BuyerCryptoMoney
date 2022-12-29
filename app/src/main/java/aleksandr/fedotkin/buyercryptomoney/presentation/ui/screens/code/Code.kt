package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.code

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel
import aleksandr.fedotkin.core.theme.Yellow
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.CodeVisualTransformation
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.CodeViewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun CodeScreen(
    data: BuyModel,
    navController: NavController,
    viewModel: CodeViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.loadProduct(productId = data.purchase.productId)
    }

    val product by viewModel.product.collectAsState()
    val code by viewModel.code.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {

        item {
            product?.let {
                Snippet(product = it, count = data.purchase.count)
            }
        }

        item {
            CodeTextField(code = code, onCodeChanged = viewModel::onCodeChanged)
        }

        item {

            Button(
                onClick = { viewModel.buy(buyModel = data, navController = navController) },
                enabled = code.length == 6,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow
                ),
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier
                    .height(height = 64.dp)
                    .width(width = 196.dp)
            ) {
                Text(
                    text = "Купить",
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Serif,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CodeTextField(code: String, onCodeChanged: (String) -> Unit) {
    TextField(
        value = code,
        onValueChange = onCodeChanged,
        modifier = Modifier
            .padding(vertical = 32.dp)
            .width(width = 196.dp)
            .border(width = 2.dp, color = Color.Gray, shape = CircleShape),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Number,
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.W600,
            fontStyle = FontStyle.Normal,
            fontSynthesis = FontSynthesis.All
        ),
        visualTransformation = CodeVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.White,
            focusedSupportingTextColor = Color.White,
            disabledIndicatorColor = Color.White
        ),
        shape = CircleShape
    )
}

@Composable
private fun Snippet(
    count: Int,
    product: ProductModel
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (image, title, rating, seller, price) = createRefs()

        val verticalGuideLine = createGuidelineFromStart(offset = 160.dp)

        CoilImage(
            imageModel = { product.imageUrl },
            modifier = Modifier
                .width(width = 128.dp)
                .constrainAs(ref = image) {
                    top.linkTo(anchor = parent.top, margin = 8.dp)
                    start.linkTo(anchor = parent.start, margin = 16.dp)
                },
            loading = {
                CircularProgressIndicator(color = Color.Black, strokeWidth = 4.dp)
            },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        )

        TitleText(
            text = product.title,
            modifier = Modifier.constrainAs(ref = title) {
                top.linkTo(anchor = parent.top, margin = 8.dp)
                start.linkTo(anchor = verticalGuideLine)
                end.linkTo(anchor = parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            })

        RatingText(
            rating = product.rating,
            modifier = Modifier.constrainAs(ref = rating) {
                top.linkTo(anchor = title.bottom, margin = 8.dp)
                start.linkTo(anchor = verticalGuideLine)
                end.linkTo(anchor = parent.end, margin = 8.dp)
                width = Dimension.fillToConstraints
            })

        SellerText(
            text = product.sellerTitle,
            modifier = Modifier.constrainAs(ref = seller) {
                top.linkTo(anchor = rating.bottom, margin = 8.dp)
                start.linkTo(anchor = verticalGuideLine)
                end.linkTo(anchor = parent.end, margin = 8.dp)
                width = Dimension.fillToConstraints
            })

        PriceText(
            price = product.price,
            count = count,
            modifier = Modifier.constrainAs(ref = price) {
                bottom.linkTo(anchor = image.bottom, margin = 4.dp)
                start.linkTo(anchor = verticalGuideLine)
                end.linkTo(anchor = parent.end, margin = 8.dp)
                width = Dimension.fillToConstraints
            })
    }
}

@Composable
private fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily.Serif,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        modifier = modifier
    )
}

@Composable
private fun RatingText(rating: Double, modifier: Modifier) {
    Text(
        text = "${"%.1f".format(rating)} / 5",
        modifier = modifier,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily.Cursive,
        fontSize = 14.sp,
        color = Color.Gray
    )
}

@Composable
private fun SellerText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily.Serif,
        fontSize = 14.sp,
        fontWeight = FontWeight.W100,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun PriceText(price: Int, count: Int, modifier: Modifier) {
    val text = multiStyleText(
        text1 = "$price P x $count = ",
        color1 = Color.Black,
        text2 = "${price * count} P",
        color2 = Color.Green
    )
    Text(
        text = text,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily.Serif,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        modifier = modifier
    )
}

private fun multiStyleText(
    text1: String,
    color1: Color,
    text2: String,
    color2: Color
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = color1)) {
            append(text1)
        }
        withStyle(style = SpanStyle(color = color2)) {
            append(text2)
        }
    }
}