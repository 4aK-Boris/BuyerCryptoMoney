package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.CardDTO
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatCvcException
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatMonthException
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatNumberCardException
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatYearException
import aleksandr.fedotkin.buyercryptomoney.domain.models.CardModel
import androidx.core.text.isDigitsOnly

class CardMapper {

    fun map(cardModel: CardModel): CardDTO {
        when {
            cardModel.number.length != NUMBER_LENGTH || !cardModel.number.isDigitsOnly() -> {
                throw InvalidFormatNumberCardException()
            }
            cardModel.cvc.length != CVC_LENGTH || !cardModel.cvc.isDigitsOnly() -> {
                throw InvalidFormatCvcException()
            }
            cardModel.month.length != YEAR_AND_MONTH_LENGTH || !cardModel.month.isDigitsOnly() -> {
                throw InvalidFormatMonthException()
            }
            cardModel.year.length != YEAR_AND_MONTH_LENGTH || !cardModel.year.isDigitsOnly() -> {
                throw InvalidFormatYearException()
            }
            else -> {
                return CardDTO(
                    number = cardModel.number,
                    month = cardModel.month.toInt(),
                    year = cardModel.year.toInt(),
                    cvc = cardModel.cvc.toInt()
                )
            }
        }
    }

    companion object {
        private const val NUMBER_LENGTH = 16
        private const val CVC_LENGTH = 3
        private const val YEAR_AND_MONTH_LENGTH = 2
    }
}
