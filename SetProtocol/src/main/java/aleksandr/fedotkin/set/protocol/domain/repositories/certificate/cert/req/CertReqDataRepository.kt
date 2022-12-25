package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.CertReqData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CertReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import java.math.BigInteger
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface CertReqDataRepository {

    val serializer: KSerializer<CertReqData>

    val convertToModel: (CertReqData) -> CertReqDataModel

    val convertToDTO: (CertReqDataModel) -> CertReqData

    suspend fun createCertReqData(
        messageWrapperModel: MessageWrapperModel<RegFormResModel>,
        data: List<String>,
        rrpid: BigInteger,
        secretKey: SecretKey
    ): CertReqDataModel
}