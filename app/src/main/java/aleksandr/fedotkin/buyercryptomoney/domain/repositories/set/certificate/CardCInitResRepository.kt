package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitRes
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel

interface CardCInitResRepository {

    fun convertToModel(cardCInitRes: CardCInitRes): CardCInitResModel

    fun convertToDTO(cardCInitResModel: CardCInitResModel): CardCInitRes
}
