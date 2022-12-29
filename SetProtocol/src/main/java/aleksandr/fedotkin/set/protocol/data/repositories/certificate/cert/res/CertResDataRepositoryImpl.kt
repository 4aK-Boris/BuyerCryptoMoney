package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.res

import aleksandr.fedotkin.set.protocol.core.SignatureFailure
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CertResData
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res.CertResDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.res.CertResDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.SignatureRepository
import java.security.cert.X509Certificate

class CertResDataRepositoryImpl(
    private val certResDataMapper: CertResDataMapper,
    private val signatureRepository: SignatureRepository
): CertResDataRepository {

    override val serializer = CertResData.serializer()

    override val convertToDTO: (CertResDataModel) -> CertResData = certResDataMapper::map

    override val convertToModel: (CertResData) -> CertResDataModel = certResDataMapper::map

    override suspend fun checkSignature(
        signature: ByteArray,
        model: CertResDataModel,
        certificate: X509Certificate
    ) {
        if (signatureRepository.verifySignature(
                model = model,
                map = convertToDTO,
                serializer = serializer,
                signatureArray = signature,
                publicKey = certificate.publicKey
            )
        ) {
            throw SignatureFailure()
        }
    }
}