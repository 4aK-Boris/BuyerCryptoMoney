package aleksandr.fedotkin.buyercryptomoney.core

import aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.main.MainScreen
import aleksandr.fedotkin.core.core.BaseActivity
import aleksandr.fedotkin.core.core.BaseViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()
    override val baseViewModel: BaseViewModel by lazy { viewModel }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    override fun Content() {
        MainScreen()
        //TestScreen()
    }
}
