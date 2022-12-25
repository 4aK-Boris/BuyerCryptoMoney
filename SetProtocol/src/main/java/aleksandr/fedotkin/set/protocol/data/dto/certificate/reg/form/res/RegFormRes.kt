package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class RegFormRes(
    val ca: String,
    val regFormResTBS: RegFormResTBS
): DTO
