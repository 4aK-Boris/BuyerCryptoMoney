package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.domain.common.data.INVALID_FORMAT_CVC_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.common.data.INVALID_FORMAT_MONTH_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.common.data.INVALID_FORMAT_NUMBER_CARD_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.common.data.INVALID_FORMAT_YEAR_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.model.BankCardModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.PurchaseModel
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.presentation.core.BaseViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BankCardViewModel(
    private val buyUseCase: BuyUseCase
) : BaseViewModel() {

    private val _number: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _month: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _year: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _cvc: MutableStateFlow<String> = MutableStateFlow(value = "")

    val number: StateFlow<String> = _number
    val month: StateFlow<String> = _month
    val year: StateFlow<String> = _year
    val cvc: StateFlow<String> = _cvc

    private val scope = viewModelScope

    fun onNumberChanged(text: String) {
        _number.value = text
    }

    fun onMonthChanged(text: String) {
        _month.value = text
    }

    fun onYearChanged(text: String) {
        _year.value = text
    }

    fun onCVCChanged(text: String) {
        _cvc.value = text
    }

    fun buy(buyerId: Int, sellerId: Int, snippetId: Int) = scope.launch {
        withContext(context = scope.coroutineContext + Dispatchers.IO) {
            val productModel = ProductModel(buyerId = buyerId, sellerId = sellerId, snippetId = snippetId)
            val bankCardModel = BankCardModel(
                number = _number.value,
                month = _month.value,
                year = _year.value,
                cvc = _cvc.value
            )
            val purchaseModel = PurchaseModel(
                bankCardModel = bankCardModel,
                productModel = productModel
            )
            val updateBuyerModel = buyUseCase.buy(purchaseModel = purchaseModel)
        }
    }

    override fun getErrorActionsMap(): Map<Int, () -> Unit> {
        return mapOf(
            INVALID_FORMAT_NUMBER_CARD_EXCEPTION to { showError(errorMessage = "Номер карты введён неверно!") },
            INVALID_FORMAT_CVC_EXCEPTION to { showError(errorMessage = "CVC введён неверно!") },
            INVALID_FORMAT_MONTH_EXCEPTION to { showError(errorMessage = "Меяц введён неверно!") },
            INVALID_FORMAT_YEAR_EXCEPTION to { showError(errorMessage = "Год введён неверно!") }
        )
    }
}