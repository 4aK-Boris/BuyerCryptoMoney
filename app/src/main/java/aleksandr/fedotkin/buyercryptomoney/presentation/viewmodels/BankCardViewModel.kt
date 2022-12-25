package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.core.exception.INVALID_FORMAT_CVC_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.core.exception.INVALID_FORMAT_MONTH_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.core.exception.INVALID_FORMAT_NUMBER_CARD_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.core.exception.INVALID_FORMAT_YEAR_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.models.CardModel
import aleksandr.fedotkin.buyercryptomoney.domain.models.PurchaseModel
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Screen
import aleksandr.fedotkin.core.BaseViewModel
import aleksandr.fedotkin.core.ErrorHandler
import aleksandr.fedotkin.core.runOnIO
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class BankCardViewModel(
    private val buyUseCase: BuyUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler = errorHandler) {

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

    fun onCountChanged(count: Int) {
        _count.value = count
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

    override val errorMap: Map<Int, String>
        get() = mapOf(
            INVALID_FORMAT_NUMBER_CARD_EXCEPTION to "Номер карты введён неверно!",
            INVALID_FORMAT_CVC_EXCEPTION to "CVC введён неверно!",
            INVALID_FORMAT_MONTH_EXCEPTION to "Меяц введён неверно!",
            INVALID_FORMAT_YEAR_EXCEPTION to "Год введён неверно!"
        )
}
