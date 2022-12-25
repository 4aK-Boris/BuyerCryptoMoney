package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitResTBS
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.SignatureRepository

class CardCInitResRepositoryImpl(
    private val cardCInitResMapper: CardCInitResMapper,
    private val keyRepository: KeyRepository,
    private val signatureRepository: SignatureRepository
) : CardCInitResRepository {

    override val serializer = CardCInitRes.serializer()

    override val convertToModel: (CardCInitRes) -> CardCInitResModel = cardCInitResMapper::map

    override val convertToDTO: (CardCInitResModel) -> CardCInitRes = cardCInitResMapper::map

    override suspend fun checkSignature(cardCInitResModel: CardCInitResModel): Boolean {
        return signatureRepository.verifySignature(
            data = convertToDTO(cardCInitResModel).cardCInitResTBS,
            serializer = CardCInitResTBS.serializer(),
            publicKey = keyRepository.decodeCertificate(certificate = cardCInitResModel.cardCInitResTBS.caeThumb).publicKey,
            signatureArray = cardCInitResModel.ca
        )
    }
}
