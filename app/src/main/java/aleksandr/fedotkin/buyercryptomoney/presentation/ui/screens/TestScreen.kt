package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens

import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.TestViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestScreen(viewModel: TestViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { viewModel.test() }) {
            Text(text = "Тест")
        }
    }
}