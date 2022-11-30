package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.BankCardDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.BankCardModel

class BankCardMapper {

    fun map(bankCardModel: BankCardModel): BankCardDTO {
        return BankCardDTO(
            number = bankCardModel.number,
            month = bankCardModel.month.toInt(),
            year = bankCardModel.year.toInt(),
            cvc = bankCardModel.cvc.toInt()
        )
    }
}