package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres

@kotlinx.serialization.Serializable
data class CAMsg(
    val cardLogoURL: String?,
    val brandLogoURL: String,
    val cardCurrency: CardCurrency?,
    val cardholderMsg: String?
)
