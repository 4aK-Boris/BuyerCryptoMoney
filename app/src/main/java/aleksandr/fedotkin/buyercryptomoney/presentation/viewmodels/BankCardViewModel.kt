package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.core.BaseViewModel
import aleksandr.fedotkin.buyercryptomoney.core.runOnIO
import aleksandr.fedotkin.buyercryptomoney.domain.common.INVALID_FORMAT_CVC_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.common.INVALID_FORMAT_MONTH_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.common.INVALID_FORMAT_NUMBER_CARD_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.common.INVALID_FORMAT_YEAR_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.CardModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.PurchaseModel
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Screen
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class BankCardViewModel(
    private val buyUseCase: BuyUseCase
) : BaseViewModel() {

    private val _number: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _month: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _year: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _cvc: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _count: MutableStateFlow<Int> = MutableStateFlow(value = 1)

    val number: StateFlow<String> = _number
    val month: StateFlow<String> = _month
    val year: StateFlow<String> = _year
    val cvc: StateFlow<String> = _cvc
    val count: StateFlow<Int> = _count

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

    fun buy(buyerId: Int, sellerId: Int, productId: Int, navController: NavController) = runOnIO {
        val purchaseModel = PurchaseModel(
            buyerId = buyerId,
            sellerId = sellerId,
            productId = productId,
            count = _count.value
        )
        val cardModel = CardModel(
            number = _number.value,
            month = _month.value,
            year = _year.value,
            cvc = _cvc.value
        )
        val buyModel = BuyModel(
            purchase = purchaseModel,
            card = cardModel
        )
        buyUseCase.buy(buyModel = buyModel).resultDefaultHandle {
            withContext(Dispatchers.Main) {
                navController.navigate(route = Screen.Products.route)
            }
        }
    }

    override fun getErrorActionsMap(): Map<Int, () -> Unit> {
        return mapOf(
            INVALID_FORMAT_NUMBER_CARD_EXCEPTION to { showError(message = "Номер карты введён неверно!") },
            INVALID_FORMAT_CVC_EXCEPTION to { showError(message = "CVC введён неверно!") },
            INVALID_FORMAT_MONTH_EXCEPTION to { showError(message = "Меяц введён неверно!") },
            INVALID_FORMAT_YEAR_EXCEPTION to { showError(message = "Год введён неверно!") }
        )
    }
}
