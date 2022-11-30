package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.PurchaseDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.PurchaseModel

class PurchaseMapper(
    private val bankCardMapper: BankCardMapper,
    private val productMapper: ProductMapper
) {

    fun map(purchaseModel: PurchaseModel): PurchaseDTO {
        return PurchaseDTO(
            productDTO = productMapper.map(productModel = purchaseModel.productModel),
            bankCardDTO = bankCardMapper.map(bankCardModel = purchaseModel.bankCardModel)
        )
    }

}