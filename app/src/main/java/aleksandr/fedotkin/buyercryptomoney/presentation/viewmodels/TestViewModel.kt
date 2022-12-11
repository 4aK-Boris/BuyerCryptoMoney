package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.CertificateUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel(private val certificateUseCase: CertificateUseCase): ViewModel() {

    fun test() = viewModelScope.launch(Dispatchers.IO) {
        certificateUseCase.getCertificate()
    }
}