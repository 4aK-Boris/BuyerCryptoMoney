package aleksandr.fedotkin.buyercryptomoney.core

import aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.main.MainScreen
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.MainViewModel
import android.os.Build
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    override val baseViewModel: BaseViewModel by lazy { viewModel }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    override fun Content() {
        MainScreen()
    }
}
