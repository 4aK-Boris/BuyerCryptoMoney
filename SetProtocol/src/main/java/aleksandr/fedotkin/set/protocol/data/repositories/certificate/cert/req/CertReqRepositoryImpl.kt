package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.BaseRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.CertReq
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.CertReqData
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.CertReqMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CertReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CertReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.PANData0Model
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncXModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req.CertReqDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req.CertReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req.PanData0Repository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EncRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EncXRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

class CertReqRepositoryImpl(
    private val keyRepository: KeyRepository,
    private val certReqDataRepository: CertReqDataRepository,
    private val panData0Repository: PanData0Repository,
    private val privateKey: PrivateKey,
    private val encRepository: EncRepository,
    private val encXRepository: EncXRepository,
    private val certReqMapper: CertReqMapper
) : CertReqRepository, BaseRepository() {

    override val serializer = CertReq.serializer()

    override val convertToDTO: (CertReqModel) -> CertReq = certReqMapper::map

    override val convertToModel: (CertReq) -> CertReqModel = certReqMapper::map

    override suspend fun createCertReqModel(
        messageWrapperModel: MessageWrapperModel<RegFormResModel>,
        data: List<String>
    ): Pair<CertReqModel, BigInteger> {
        val rrpid = generateNewNumber()
        val secretKey = keyRepository.generateSecretKey()
        val certReqDataModel = certReqDataRepository.createCertReqData(
            messageWrapperModel = messageWrapperModel,
            data = data,
            secretKey = secretKey,
            rrpid = rrpid
        )
        val certificate =
            keyRepository.decodeCertificate(certificate = messageWrapperModel.messageModel.regFormResTBS.caeThumb)
        val panDataModel =
            panData0Repository.createPANData(month = data[1], year = data[2], number = data[0])
        return createCertReq(
            model = certReqDataModel,
            panDataModel = panDataModel,
            certificate = certificate
        ) to rrpid
    }

    private suspend fun createCertReq(
        model: CertReqDataModel,
        panDataModel: PANData0Model,
        certificate: X509Certificate
    ): CertReqModel {
        return CertReqModel(
            enc = createEnc(model = model, certificate = certificate),
            encX = createEncX(model = model, certificate = certificate, panDataModel = panDataModel)
        )
    }

    private suspend fun createEnc(model: CertReqDataModel, certificate: X509Certificate): EncModel {
        return encRepository.encrypt(
            model = model,
            map = certReqDataRepository.convertToDTO,
            serializer = CertReqData.serializer(),
            certificate = certificate,
            privateKey = privateKey
        )
    }

    private suspend fun createEncX(
        model: CertReqDataModel,
        panDataModel: PANData0Model,
        certificate: X509Certificate
    ): EncXModel {
        return encXRepository.encrypt(
            data = model,
            secondaryData = panDataModel,
            map = certReqDataRepository.convertToDTO,
            secondaryMap = panData0Repository.convertToDTO,
            serializer = certReqDataRepository.serializer,
            secondarySerializer = panData0Repository.serializer,
            certificate = certificate,
            privateKey = privateKey
        )
    }
}