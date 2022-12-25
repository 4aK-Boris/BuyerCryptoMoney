package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.BaseRepository
import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.CertReqData
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.CertReqDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CABackKeyDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CertReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.PublicKeySorEModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.RegFormItemsModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req.CertReqDataRepository
import java.math.BigInteger
import java.security.PublicKey
import java.time.LocalDateTime
import javax.crypto.SecretKey

class CertReqDataRepositoryImpl(
    private val certReqDataMapper: CertReqDataMapper,
    private val publicKey: PublicKey
): CertReqDataRepository, BaseRepository() {

    override val serializer = CertReqData.serializer()

    override val convertToDTO: (CertReqDataModel) -> CertReqData = certReqDataMapper::map

    override val convertToModel: (CertReqData) -> CertReqDataModel = certReqDataMapper::map

    override suspend fun createCertReqData(
        messageWrapperModel: MessageWrapperModel<RegFormResModel>,
        data: List<String>,
        rrpid: BigInteger,
        secretKey: SecretKey
    ): CertReqDataModel {
        with(messageWrapperModel.messageModel.regFormResTBS) {
            return CertReqDataModel(
                rrpID = rrpid,
                lidEE = lidEE,
                challCA = challCA,
                challEE3 = generateNewNumber(),
                lidCA = lidCA,
                requestType = requestType,
                idData = null,
                regFormID = regFormOrReferral.referralData.regFormID,
                regForm = createRegForm(
                    regFieldSeq = regFormOrReferral.referralData.regFieldSeq,
                    data = data
                ),
                caBackKeyData = CABackKeyDataModel(caaIgId = CIPHER_ALGORITHM, caKey = secretKey),
                publicKeySorE = PublicKeySorEModel(publicKeyE = null, publicKeyS = publicKey),
                eeThumb = byteArrayOf(),
                thumbs = thumbs,
                requestDate = dateTime
            )
        }
    }

    private fun createRegForm(
        regFieldSeq: List<RegFieldModel>,
        data: List<String>
    ): List<RegFormItemsModel> {
        return regFieldSeq.zip(data).map { (regFieldModel, value) ->
            RegFormItemsModel(
                fieldName = regFieldModel.fieldName.first(),
                fieldValue = value
            )
        }
    }

    private val dateTime: LocalDateTime
        get() = LocalDateTime.now()
}