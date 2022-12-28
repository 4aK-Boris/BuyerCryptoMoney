package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class PANOnly(
    val pan: String,
    val exNonce: String
): DTO