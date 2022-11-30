package aleksandr.fedotkin.buyercryptomoney.domain.model

data class PurchaseModel(
    val productModel: ProductModel,
    val bankCardModel: BankCardModel
)