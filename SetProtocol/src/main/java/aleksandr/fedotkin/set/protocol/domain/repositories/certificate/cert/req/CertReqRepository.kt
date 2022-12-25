package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.CertReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CertReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import java.math.BigInteger
import kotlinx.serialization.KSerializer

interface CertReqRepository {

    val serializer: KSerializer<CertReq>

    val convertToModel: (CertReq) -> CertReqModel

    val convertToDTO: (CertReqModel) -> CertReq

    suspend fun createCertReqModel(
        messageWrapperModel: MessageWrapperModel<RegFormResModel>,
        data: List<String>
    ): Pair<CertReqModel, BigInteger>
}