package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certres

import java.math.BigInteger

data class CertResDataModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE3: BigInteger,
    val lidCA: BigInteger,
    val certStatus: CertStatusModel,
    val certThumbs: List<ByteArray>,
    val brandCRLIdentifier: List<ByteArray>,
    val thumbs: List<ByteArray>
)
