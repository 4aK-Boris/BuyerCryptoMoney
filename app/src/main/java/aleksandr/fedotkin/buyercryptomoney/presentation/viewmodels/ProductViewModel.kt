package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.core.BaseViewModel
import aleksandr.fedotkin.buyercryptomoney.core.ErrorHandler
import aleksandr.fedotkin.buyercryptomoney.core.runOnIO
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyerUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.ProductUseCase
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Screen
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlin.math.min

class ProductViewModel(
    private val productUseCase: ProductUseCase,
    private val buyerUseCase: BuyerUseCase,
    private val defaultValue: BuyerModel,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler = errorHandler) {

    private val _buyers: MutableStateFlow<List<BuyerModel>> =
        MutableStateFlow(value = emptyList())
    private val _buyerIndex: MutableStateFlow<Int> = MutableStateFlow(value = 0)
    private val _products: MutableStateFlow<List<ProductModel>> =
        MutableStateFlow(value = emptyList())

    val buyers: StateFlow<List<BuyerModel>> = _buyers

    val buyer: Flow<BuyerModel> =
        _buyerIndex.map { _buyers.value.getOrElse(index = it, defaultValue = { defaultValue }) }
    val products: StateFlow<List<ProductModel>> = _products

    fun loadProducts() = runOnIO {
        productUseCase.getProducts().resultDefaultHandle {
            _products.value = it
        }
    }

    suspend fun loadBuyers() = runOnIO {
        buyerUseCase.getBuyers().resultDefaultHandle {
            _buyers.value = it
        }
    }

    fun setBuyerIndex(buyerIndex: Int) {
        _buyerIndex.value = buyerIndex
    }

    fun navigate(navController: NavController, productModel: ProductModel) {
        val maxCount = _buyers.value[_buyerIndex.value].amountOfMoney / productModel.price
        val route = Screen.Card.createRoute(
            buyerId = _buyerIndex.value,
            sellerId = productModel.sellerId,
            productId = productModel.id,
            maxCount = min(productModel.quantity, maxCount)
        )
        navController.navigate(route = route)
    }
}
