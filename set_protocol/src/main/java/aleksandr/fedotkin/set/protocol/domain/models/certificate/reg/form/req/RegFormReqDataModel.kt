package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger

data class RegFormReqDataModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE2: BigInteger,
    val lidCA: BigInteger,
    val requestType: RequestType,
    val language: Language,
    val thumbs: List<ByteArray>
) : Model
