package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.core.runOnIO
import aleksandr.fedotkin.buyercryptomoney.core.exception.INVALID_CODE
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.AccuracyUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.ProductUseCase
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Screen
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class CodeViewModel(
    errorHandler: ErrorHandler,
    private val productUseCase: ProductUseCase,
    private val buyUseCase: BuyUseCase,
    private val accuracyUseCase: AccuracyUseCase
) : BaseViewModel(errorHandler = errorHandler) {

    private val _product: MutableStateFlow<ProductModel?> = MutableStateFlow(value = null)
    private val _code: MutableStateFlow<String> = MutableStateFlow(value = "")

    val product: StateFlow<ProductModel?> = _product
    val code: StateFlow<String> = _code

    fun loadProduct(productId: Int) = runOnIO {
        productUseCase.getProduct(productId = productId).resultDefaultHandle {
            _product.value = it
        }
    }

    fun onCodeChanged(code: String) {
        if (code.length <= 6) {
            _code.value = code
        }
    }

    fun buy(navController: NavController, buyModel: BuyModel) = runOnIO {
        accuracyUseCase.checkCode(code = _code.value).resultDefaultHandle {
            buyUseCase.checkCode(code = it).resultDefaultHandle {
                buyUseCase.buy(buyModel = buyModel).resultDefaultHandle {
                    withContext(Dispatchers.Main) {
                        navController.navigate(route = Screen.Products.route)
                    }
                }
            }
        }
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(INVALID_CODE to "Код подтверждения введён неверно!")
}