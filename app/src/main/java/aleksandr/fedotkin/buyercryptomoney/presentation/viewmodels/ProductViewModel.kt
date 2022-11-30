package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.domain.common.data.INVALID_FORMAT_YEAR_EXCEPTION
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.SellerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.SnippetModel
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.DataUseCase
import aleksandr.fedotkin.buyercryptomoney.presentation.core.BaseViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.core.runOnIO
import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Screen
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel(
    private val dataUseCase: DataUseCase
) : BaseViewModel() {

    private val _snippets: MutableStateFlow<List<SnippetModel>> =
        MutableStateFlow(value = emptyList())
    private val _buyers: MutableStateFlow<List<BuyerModel>> = MutableStateFlow(value = emptyList())
    private val _buyer: MutableStateFlow<BuyerModel> = MutableStateFlow(value = defaultBuyer)

    val snippets: StateFlow<List<SnippetModel>> = _snippets
    val buyers: StateFlow<List<BuyerModel>> = _buyers
    val buyer: StateFlow<BuyerModel> = _buyer

    fun loadSnippets() = runOnIO {
        _snippets.value = dataUseCase.getSnippets()
    }

    suspend fun loadSeller(id: Int): SellerModel {
        return dataUseCase.getSeller(id = id)
    }

    suspend fun loadBuyers() = runOnIO {
        _buyers.value = dataUseCase.getBuyers()
        _buyer.value = _buyers.value.first()
    }

    fun setBuyer(index: Int) {
        _buyer.value = _buyers.value.getOrElse(index = index, defaultValue = { defaultBuyer })
    }

    fun navigate(navController: NavController, snippet: SnippetModel) {
        val route = Screen.BankCard.createRoute(
            buyerId = _buyer.value.id,
            sellerId = snippet.sellerId,
            snippetId = snippet.id
        )
        navController.navigate(route = route)
    }

    companion object {
        private val defaultBuyer = BuyerModel(
            id = 0,
            nickName = "Тащер",
            imageUrl = "https://sun9-east.userapi.com/sun9-60/s/v1/ig2/80TOhayqCnWijE6DO_rymIUsR-DNauDbX17-5D6cu3Msvmsk97bo44ZJsUZ7sF-thljTpnu--ezCha1I4lqisim1.jpg?size=629x697&quality=96&type=album",
            amountOfMoney = 1000000
        )
    }

    override fun getErrorActionsMap(): Map<Int, () -> Unit> {
        return mapOf(INVALID_FORMAT_YEAR_EXCEPTION to { showError(errorMessage = "Год введён неверно!") })
    }
}