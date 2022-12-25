package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.core.ChallengeMismatch
import aleksandr.fedotkin.set.protocol.core.ThumbsMismatch
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CertRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.res.CertResRepository
import java.math.BigInteger
import java.security.cert.X509Certificate
import javax.crypto.SecretKey

class CertResUseCase(
    private val certResRepository: CertResRepository
) : ResponseUseCase<CertResModel, CertRes>() {

    override val serializer = certResRepository.serializer

    override val convertToDTO = certResRepository.convertToDTO

    override val convertToModel = certResRepository.convertToModel

    suspend fun checkMessageWrapper(
        messageWrapperJson: String,
        certificate: X509Certificate,
        secretKey: SecretKey,
        thumbs: List<ByteArray>,
        lidEE: BigInteger,
        challEE: BigInteger
    ) {
        val messageWrapperModel =
            checkMessageWrapperAndConvertToModel(messageWrapperJson = messageWrapperJson)
        val certResDataModel = certResRepository.decryptData(
            encKModel = messageWrapperModel.messageModel.encK,
            certificate = certificate,
            secretKey = secretKey
        )
        certResRepository.checkSignature(
            signature = messageWrapperModel.messageModel.signature,
            model = certResDataModel,
            certificate = certificate
        )
        checkThumbs(certResDataModel = certResDataModel, thumbs = thumbs)
        checkLIDEE(certResDataModel = certResDataModel, lidEE = lidEE)
        checkChallEE(certResDataModel = certResDataModel, challEE = challEE)
    }

    private fun checkThumbs(certResDataModel: CertResDataModel, thumbs: List<ByteArray>) {
        if (certResDataModel.thumbs != thumbs) {
            throw ThumbsMismatch()
        }
    }

    private fun checkLIDEE(certResDataModel: CertResDataModel, lidEE: BigInteger) {
        if (certResDataModel.lidEE != lidEE) {
            throw ChallengeMismatch()
        }
    }

    private fun checkChallEE(certResDataModel: CertResDataModel, challEE: BigInteger) {
        if (certResDataModel.challEE3 != challEE) {
            throw ChallengeMismatch()
        }
    }
}