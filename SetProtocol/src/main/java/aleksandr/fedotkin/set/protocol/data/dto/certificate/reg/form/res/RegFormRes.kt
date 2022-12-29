package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class RegFormRes(
    val signature: String,
    val regFormResTBS: RegFormResTBS
): DTO
