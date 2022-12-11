package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitRes
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel

class CardCInitResMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val cardCInitResTBSMapper: CardCInitResTBSMapper
) {

    fun map(dto: CardCInitRes): CardCInitResModel {
        return CardCInitResModel(
            ca = byteArrayMapper.map(string = dto.ca),
            cardCInitResTBS = cardCInitResTBSMapper.map(dto = dto.cardCInitResTBS)
        )
    }

    fun map(model: CardCInitResModel): CardCInitRes {
        return CardCInitRes(
            ca = byteArrayMapper.map(byteArray = model.ca),
            cardCInitResTBS = cardCInitResTBSMapper.map(model = model.cardCInitResTBS)
        )
    }
}
