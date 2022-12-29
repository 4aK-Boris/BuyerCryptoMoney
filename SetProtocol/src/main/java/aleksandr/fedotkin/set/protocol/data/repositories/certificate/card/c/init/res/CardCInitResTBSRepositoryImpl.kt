package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.contentEquals
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitResTBS
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SignatureRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

class CardCInitResTBSRepositoryImpl(
    override val mapper: CardCInitResTBSMapper,
    private val keyRepository: KeyRepository,
    private val certificate: X509Certificate,
    private val privateKey: PrivateKey,
    private val signatureRepository: SignatureRepository<CardCInitResTBSModel, CardCInitResTBS>,
) : CardCInitResTBSRepository {

    override suspend fun verifySignature(model: CardCInitResTBSModel, signature: ByteArray): Boolean {
        return signatureRepository.verify(
            data = model,
            signatureArray = signature,
            publicKey = decodeCertificate(certificate = model.caeThumb).publicKey
        )
    }

    override suspend fun create(model: CardCInitReqModel): CardCInitResTBSModel {
        return CardCInitResTBSModel(
            rrpID = model.rrpID,
            lidEE = model.lidEE,
            challEE = model.challEE,
            lidCA = generateNewNumber(),
            brandCRLIdentifier = emptyList(),
            thumbs = model.thumbs,
            caeThumb = certificate.encoded
        )
    }

    override suspend fun createSignature(model: CardCInitResTBSModel): ByteArray {
        return signatureRepository.create(data = model, privateKey = privateKey)
    }

    override suspend fun checkRRPID(rrpid: BigInteger?, secondRRPID: BigInteger, thirdRRPID: BigInteger): Boolean {
        return rrpid == thirdRRPID && secondRRPID == thirdRRPID
    }

    override suspend fun checkChallEE(challEE: BigInteger, secondChallEE: BigInteger): Boolean {
        return challEE == secondChallEE
    }

    override suspend fun checkLIDEE(lidEE: BigInteger, secondLIDEE: BigInteger): Boolean {
        return lidEE == secondLIDEE
    }

    override suspend fun checkThumbs(thumbs: List<ByteArray>, secondThumbs: List<ByteArray>): Boolean {
        return thumbs.contentEquals(other = secondThumbs)
    }

    private fun decodeCertificate(certificate: ByteArray): X509Certificate {
        return keyRepository.decodeCertificate(certificate = certificate)
    }
}