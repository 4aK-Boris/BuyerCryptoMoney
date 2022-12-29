package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {

    fun test() = viewModelScope.launch(Dispatchers.IO) {

    }
}