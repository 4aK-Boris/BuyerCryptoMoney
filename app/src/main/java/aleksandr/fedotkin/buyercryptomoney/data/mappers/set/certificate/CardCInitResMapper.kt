package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitRes
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel

class CardCInitResMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val cardCInitResTBSMapper: CardCInitResTBSMapper
) {

    fun map(cardCInitRes: CardCInitRes): CardCInitResModel {
        return CardCInitResModel(
            ca = byteArrayMapper.map(string = cardCInitRes.ca),
            cardCInitResTBS = cardCInitResTBSMapper.map(cardCInitResTBS = cardCInitRes.cardCInitResTBS)
        )
    }

    fun map(cardCInitResModel: CardCInitResModel): CardCInitRes {
        return CardCInitRes(
            ca = byteArrayMapper.map(byteArray = cardCInitResModel.ca),
            cardCInitResTBS = cardCInitResTBSMapper.map(cardCInitResTBSModel = cardCInitResModel.cardCInitResTBS)
        )
    }
}