package aleksandr.fedotkin.buyercryptomoney.presentation.core

import aleksandr.fedotkin.buyercryptomoney.domain.common.data.BAD_REQUEST
import aleksandr.fedotkin.buyercryptomoney.domain.common.data.INTERNAL_SERVER_ERROR
import aleksandr.fedotkin.buyercryptomoney.domain.common.data.NO_INTERNET
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow(value = "")

    val errorMessage: StateFlow<String> = _errorMessage

    private val loginErrorsMap: Map<Int, () -> Unit> by lazy { initErrorMap() }

    abstract fun getErrorActionsMap(): Map<Int, () -> Unit>

    private fun initErrorMap(): Map<Int, () -> Unit> =
        getErrorActionsMap().toMutableMap().apply {
            putIfAbsent(BAD_REQUEST) { showError(errorMessage = "Проблема на стороне клиента!") }
            putIfAbsent(INTERNAL_SERVER_ERROR) { showError(errorMessage = "Ошибка сервера!") }
            putIfAbsent(NO_INTERNET) { showError(errorMessage = "Нет интернета!") }
        }

    fun showError(errorMessage: String) {
        _errorMessage.value = errorMessage
    }

    fun handleError(errorId: Int) {
        loginErrorsMap[errorId]?.invoke() ?: showError("Unknown error: $errorId")
    }
}