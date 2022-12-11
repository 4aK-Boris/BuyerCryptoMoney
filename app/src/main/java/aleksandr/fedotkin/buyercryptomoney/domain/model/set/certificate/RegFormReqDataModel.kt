package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.Language
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RequestType
import java.math.BigInteger

data class RegFormReqDataModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE2: BigInteger,
    val lidCA: BigInteger,
    val requestType: RequestType,
    val language: Language,
    val thumbs: List<ByteArray>
)
