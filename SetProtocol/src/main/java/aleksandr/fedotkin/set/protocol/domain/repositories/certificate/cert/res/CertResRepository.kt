package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CertRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CertResModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncKModel
import java.security.cert.X509Certificate
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface CertResRepository {

    val serializer: KSerializer<CertRes>

    val convertToModel: (CertRes) -> CertResModel

    val convertToDTO: (CertResModel) -> CertRes

    suspend fun checkSignature(
        signature: ByteArray,
        model: CertResDataModel,
        certificate: X509Certificate
    )

    suspend fun decryptData(
        encKModel: EncKModel,
        certificate: X509Certificate,
        secretKey: SecretKey
    ): CertResDataModel
}