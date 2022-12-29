package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidCode
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatCvcException
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatMonthException
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatNumberCardException
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidFormatYearException
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result
import aleksandr.fedotkin.buyercryptomoney.domain.model.CardModel
import androidx.core.text.isDigitsOnly

class AccuracyUseCase : BaseUseCase() {

    suspend fun checkCardData(cardModel: CardModel): Result<Unit> = safeCall {
        checkCardNumber(number = cardModel.number)
        checkYear(year = cardModel.year)
        checkMonth(month = cardModel.month)
        checkCVC(cvc = cardModel.cvc)
    }

    suspend fun checkCode(code: String): Result<Int> = safeCall {
        if (code.length != CODE_LENGTH || !code.isDigitsOnly()) {
            throw InvalidCode()
        }
        code.toInt()
    }

    private fun checkCardNumber(number: String) {
        if (number.length != CARD_NUMBER_LENGTH || !number.isDigitsOnly()) {
            throw InvalidFormatNumberCardException()
        }
    }

    private fun checkYear(year: String) {
        if (year.length != YEAR_AND_MONTH_LENGTH || !year.isDigitsOnly() || year.toInt() < 22) {
            throw InvalidFormatYearException()
        }
    }


    private fun checkMonth(month: String) {
        if (month.length != YEAR_AND_MONTH_LENGTH || !month.isDigitsOnly() || month.toInt() > 12) {
            throw InvalidFormatMonthException()
        }
    }


    private fun checkCVC(cvc: String) {
        if (cvc.length != CVC_LENGTH || !cvc.isDigitsOnly()) {
            throw InvalidFormatCvcException()
        }
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
        private const val YEAR_AND_MONTH_LENGTH = 2
        private const val CVC_LENGTH = 3
        private const val CODE_LENGTH = 6
    }
}