package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.set.protocol.domain.useceses.CertificateUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel(private val certificateUseCase: CertificateUseCase): ViewModel() {

    fun test() = viewModelScope.launch(Dispatchers.IO) {
        certificateUseCase.getCertificate(number = "1234123412341234")
    }
}