package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitRes
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitResRepository

class CardCInitResRepositoryImpl(
    private val cardCInitResMapper: CardCInitResMapper
) : CardCInitResRepository {

    override fun convertToModel(cardCInitRes: CardCInitRes): CardCInitResModel {
        return cardCInitResMapper.map(dto = cardCInitRes)
    }

    override fun convertToDTO(cardCInitResModel: CardCInitResModel): CardCInitRes {
        return cardCInitResMapper.map(model = cardCInitResModel)
    }
}
