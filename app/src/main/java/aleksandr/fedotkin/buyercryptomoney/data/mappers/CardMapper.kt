package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.CardDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.CardModel

class CardMapper {

    fun map(cardModel: CardModel): CardDTO {
        return CardDTO(
            number = cardModel.number,
            month = cardModel.month.toInt(),
            year = cardModel.year.toInt(),
            cvc = cardModel.cvc.toInt()
        )
    }
}
