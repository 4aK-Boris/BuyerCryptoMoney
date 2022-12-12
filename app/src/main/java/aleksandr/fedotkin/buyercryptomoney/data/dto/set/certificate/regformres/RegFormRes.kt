package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres

@kotlinx.serialization.Serializable
data class RegFormRes(
    val ca: String,
    val regFormResTBS: RegFormResTBS
)
