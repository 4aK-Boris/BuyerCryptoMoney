package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.card

import aleksandr.fedotkin.buyercryptomoney.R
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.theme.Yellow
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.BankCardCVCVisualTransformation
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.BankCardMonthAndYearVisualTransformation
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.BankCardNumberVisualTransformation
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.BankCardViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun Card(
    viewModel: BankCardViewModel = koinViewModel(),
    navController: NavController,
    buyerId: Int,
    sellerId: Int,
    productId: Int,
    maxCount: Int
) {

    val number by viewModel.number.collectAsState()
    val month by viewModel.month.collectAsState()
    val year by viewModel.year.collectAsState()
    val cvc by viewModel.cvc.collectAsState()
    val count by viewModel.count.collectAsState()

    val focusManager = LocalFocusManager.current

    val onTextChanged = fun(text: String, setText: (String) -> Unit, length: Int) {
        val newText = if (text.length >= length) {
            text.take(length)
        } else {
            text
        }
        setText(newText)
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (image, numberTextField, monthTextField, yearTextField, cvcTextField, countChanger, button) = createRefs()

        Image(painter = painterResource(id = R.drawable.payform2),
            contentDescription = "Банкоская карта",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .constrainAs(ref = image) {
                    top.linkTo(anchor = parent.top, margin = 32.dp)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                }
                .fillMaxWidth())

        NumberTextEdit(number = number,
            visualTransformation = BankCardNumberVisualTransformation(),
            focusManager = focusManager,
            modifier = Modifier.constrainAs(ref = numberTextField) {
                top.linkTo(anchor = image.top, margin = 105.dp)
                start.linkTo(anchor = image.start, margin = 36.dp)
                end.linkTo(anchor = image.end, margin = 30.dp)
                width = Dimension.matchParent
            }) {
            onTextChanged(it, viewModel::onNumberChanged, BankCardNumberVisualTransformation.LENGTH)
        }

        NumberTextEdit(number = month,
            visualTransformation = BankCardMonthAndYearVisualTransformation(),
            focusManager = focusManager,
            modifier = Modifier.constrainAs(ref = monthTextField) {
                top.linkTo(anchor = image.top, margin = 186.dp)
                start.linkTo(anchor = image.start, margin = 36.dp)
                width = Dimension.value(64.dp)
            }) {
            onTextChanged(
                it, viewModel::onMonthChanged, BankCardMonthAndYearVisualTransformation.LENGTH
            )
        }

        NumberTextEdit(number = year,
            visualTransformation = BankCardMonthAndYearVisualTransformation(),
            focusManager = focusManager,
            modifier = Modifier.constrainAs(ref = yearTextField) {
                top.linkTo(anchor = image.top, margin = 186.dp)
                start.linkTo(anchor = image.start, margin = 120.dp)
                width = Dimension.value(64.dp)
            }) {
            onTextChanged(
                it, viewModel::onYearChanged, BankCardMonthAndYearVisualTransformation.LENGTH
            )
        }

        NumberTextEdit(number = cvc,
            visualTransformation = BankCardCVCVisualTransformation(),
            focusManager = focusManager,
            isCVC = true,
            modifier = Modifier.constrainAs(ref = cvcTextField) {
                top.linkTo(anchor = image.top, margin = 182.dp)
                end.linkTo(anchor = image.end, margin = 32.dp)
                width = Dimension.value(80.dp)
            }) {
            onTextChanged(it, viewModel::onCVCChanged, BankCardCVCVisualTransformation.LENGTH)
        }

        CountChanger(count = count,
            maxCount = maxCount,
            onCountChanged = viewModel::onCountChanged,
            modifier = Modifier.constrainAs(ref = countChanger) {
                top.linkTo(anchor = image.bottom, margin = 32.dp)
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = parent.end)
            })

        Button(onClick = {
            viewModel.getCode(
                buyerId = buyerId,
                sellerId = sellerId,
                productId = productId,
                navController = navController
            )
        },
            enabled = count != 0,
            colors = ButtonDefaults.buttonColors(
                containerColor = Yellow
            ),
            shape = RoundedCornerShape(size = 8.dp),
            modifier = Modifier
                .height(height = 64.dp)
                .width(width = 196.dp)
                .constrainAs(ref = button) {
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    top.linkTo(anchor = countChanger.bottom)
                    bottom.linkTo(anchor = parent.bottom)
                }) {
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

@Composable
private fun CountChanger(
    count: Int, maxCount: Int, onCountChanged: (Int) -> Unit, modifier: Modifier
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .border(width = 3.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                if (count > 0) {
                    onCountChanged(count - 1)
                }
            }, modifier = Modifier.size(48.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "Уменьшить количество"
                )
            }
        }

        Text(
            text = "$count",
            modifier = Modifier.width(64.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            fontSize = 32.sp,
            fontWeight = FontWeight.W600
        )

        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                if (count < maxCount) {
                    onCountChanged(count + 1)
                }
            }, modifier = Modifier.size(48.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Увеличить количество"
                )
            }
        }
    }
}

@Composable
private fun NumberTextEdit(
    number: String,
    modifier: Modifier,
    focusManager: FocusManager,
    visualTransformation: VisualTransformation,
    isCVC: Boolean = false,
    onNumberChanged: (String) -> Unit
) {

    Box(
        modifier = modifier
            .height(42.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(size = 2.dp))
            .background(color = Color.White, shape = RoundedCornerShape(size = 2.dp)),
        contentAlignment = Alignment.Center
    ) {

        BasicTextField(
            value = number,
            onValueChange = onNumberChanged,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Number,
                imeAction = if (isCVC) ImeAction.Done else ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) },
                onDone = { focusManager.clearFocus() }),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.W600,
                fontStyle = FontStyle.Normal,
                fontSynthesis = FontSynthesis.All
            ),
            visualTransformation = visualTransformation
        )
    }
}
