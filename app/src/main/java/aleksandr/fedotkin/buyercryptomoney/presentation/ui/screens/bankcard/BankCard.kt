package aleksandr.fedotkin.buyercryptomoney.ui

import aleksandr.fedotkin.buyercryptomoney.R
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.theme.Yellow
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.BankCardViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.BankCardCVCVisualTransformation
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.BankCardMonthAndYearVisualTransformation
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation.BankCardNumberVisualTransformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun BankCard(
    viewModel: BankCardViewModel = koinViewModel(),
    navController: NavController,
    buyerId: Int,
    sellerId: Int,
    snippetId: Int
) {

    val number by viewModel.number.collectAsState()
    val month by viewModel.month.collectAsState()
    val year by viewModel.year.collectAsState()
    val cvc by viewModel.cvc.collectAsState()

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

        val (image, numberTextField, monthTextField, yearTextField, cvcTextField, button) = createRefs()

        Image(painter = painterResource(id = R.drawable.payform2),
            contentDescription = "Банкоская карта",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .constrainAs(ref = image) {
                    top.linkTo(anchor = parent.top, margin = 32.dp)
                    start.linkTo(anchor = parent.start, margin = 16.dp)
                    end.linkTo(anchor = parent.end, margin = 16.dp)
                }
                .fillMaxWidth()
        )

        NumberTextEdit(number = number,
            visualTransformation = BankCardNumberVisualTransformation(),
            focusManager = focusManager,
            modifier = Modifier.constrainAs(ref = numberTextField) {
                top.linkTo(anchor = image.top, margin = 98.dp)
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
                top.linkTo(anchor = image.top, margin = 176.dp)
                start.linkTo(anchor = image.start, margin = 36.dp)
                width = Dimension.value(64.dp)
            }) {
            onTextChanged(
                it,
                viewModel::onMonthChanged,
                BankCardMonthAndYearVisualTransformation.LENGTH
            )
        }

        NumberTextEdit(number = year,
            visualTransformation = BankCardMonthAndYearVisualTransformation(),
            focusManager = focusManager,
            modifier = Modifier.constrainAs(ref = yearTextField) {
                top.linkTo(anchor = image.top, margin = 176.dp)
                start.linkTo(anchor = image.start, margin = 114.dp)
                width = Dimension.value(64.dp)
            }) {
            onTextChanged(
                it,
                viewModel::onYearChanged,
                BankCardMonthAndYearVisualTransformation.LENGTH
            )
        }

        NumberTextEdit(number = cvc,
            visualTransformation = BankCardCVCVisualTransformation(),
            focusManager = focusManager,
            isCVC = true,
            modifier = Modifier.constrainAs(ref = cvcTextField) {
                top.linkTo(anchor = image.top, margin = 172.dp)
                end.linkTo(anchor = image.end, margin = 30.dp)
                width = Dimension.value(80.dp)
            }) {
            onTextChanged(it, viewModel::onCVCChanged, BankCardCVCVisualTransformation.LENGTH)
        }

        Button(
            onClick = {
                viewModel.buy(buyerId = buyerId, sellerId = sellerId, snippetId = snippetId)
            },
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
                    top.linkTo(anchor = image.bottom)
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
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) },
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