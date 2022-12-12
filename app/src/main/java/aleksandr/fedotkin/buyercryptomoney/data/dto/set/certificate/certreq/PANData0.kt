package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

import kotlinx.datetime.LocalDate

@kotlinx.serialization.Serializable
data class PANData0(
    val pan: String,
    val cardExpiry: LocalDate,
    val cardSecret: String,
    val exNonce: String
)
