package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CertRes
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res.CertResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncKModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.res.CertResDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.res.CertResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EncKRepository
import java.security.cert.X509Certificate
import javax.crypto.SecretKey

class CertResRepositoryImpl(
    private val certResMapper: CertResMapper,
    private val encKRepository: EncKRepository,
    private val certResDataRepository: CertResDataRepository
) : CertResRepository {

    override val serializer = CertRes.serializer()

    override val convertToDTO: (CertResModel) -> CertRes = certResMapper::map

    override val convertToModel: (CertRes) -> CertResModel = certResMapper::map

    override suspend fun checkSignature(
        signature: ByteArray,
        model: CertResDataModel,
        certificate: X509Certificate
    ) {
        certResDataRepository.checkSignature(signature, model, certificate)
    }

    override suspend fun decryptData(
        encKModel: EncKModel,
        certificate: X509Certificate,
        secretKey: SecretKey
    ): CertResDataModel {
        return encKRepository.decrypt(
            model = encKModel,
            secretKey = secretKey,
            certificate = certificate,
            serializer = certResDataRepository.serializer,
            map = certResDataRepository.convertToModel
        )
    }
}