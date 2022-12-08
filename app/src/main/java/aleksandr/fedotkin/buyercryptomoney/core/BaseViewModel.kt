package aleksandr.fedotkin.buyercryptomoney.core

import aleksandr.fedotkin.buyercryptomoney.domain.common.BAD_REQUEST
import aleksandr.fedotkin.buyercryptomoney.domain.common.INTERNAL_SERVER_ERROR
import aleksandr.fedotkin.buyercryptomoney.domain.common.NO_INTERNET
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel: ViewModel() {

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _errorState: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    val errorMessage: StateFlow<String> = _errorMessage
    val errorState: StateFlow<Boolean> = _errorState

    fun close() {
        _errorState.value = false
    }

    private val loginErrorsMap: Map<Int, () -> Unit> by lazy { initErrorMap() }

    abstract fun getErrorActionsMap(): Map<Int, () -> Unit>

    private fun initErrorMap(): Map<Int, () -> Unit> =
        getErrorActionsMap().toMutableMap().apply {
            putIfAbsent(BAD_REQUEST) { showError("Проблема на стороне клиента!") }
            putIfAbsent(INTERNAL_SERVER_ERROR) { showError("Проблема с сервером") }
            putIfAbsent(NO_INTERNET) { showError("Пожалуйста, проверьте сетевое подключение") }
        }


    protected fun showError(message: String) {
        _errorState.value = true
        _errorMessage.value = message
    }

    fun handleError(errorId: Int) {
        loginErrorsMap[errorId]?.invoke() ?: showError("Unknown error: $errorId")
    }

    suspend fun <T> Result<T>.resultDefaultHandle(successBlock: suspend (T) -> Unit) {
        when (this) {
            is Result.Success -> successBlock(data)
            is Result.Error -> handleError(extraErrorCode)
        }
    }
}
