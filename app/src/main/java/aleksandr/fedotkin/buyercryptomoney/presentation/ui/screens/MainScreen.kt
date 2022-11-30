package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens

import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Navigation
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {

    //val errorMessage by viewModel.errorMessage.collectAsState()

    //var errorDialogState by remember { mutableStateOf(value = false) }


//    LaunchedEffect(key1 = errorMessage) {
//        errorDialogState = errorMessage.isNotBlank()
//    }
//
//    ErrorDialog(state = errorDialogState, errorMessage = errorMessage) {
//        errorDialogState = false
//    }

    Navigation()
}

@Composable
private fun ErrorDialog(state: Boolean, errorMessage: String, closeDialog: () -> Unit) {
    if (state) {
        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(text = "Ошибка!")
            },
            text = {
                Text(text = errorMessage)
            },
            confirmButton = {
                Button(onClick = closeDialog) {
                    Text(text = "ОК")
                }
            }
        )
    }
}