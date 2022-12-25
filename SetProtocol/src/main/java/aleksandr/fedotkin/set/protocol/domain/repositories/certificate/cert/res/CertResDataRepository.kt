package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CertResData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResDataModel
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface CertResDataRepository {

    val serializer: KSerializer<CertResData>

    val convertToModel: (CertResData) -> CertResDataModel

    val convertToDTO: (CertResDataModel) -> CertResData

    suspend fun checkSignature(
        signature: ByteArray,
        model: CertResDataModel,
        certificate: X509Certificate
    )
}