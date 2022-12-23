package aleksandr.fedotkin.set.protocol.data.dto.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class PANOnly(
    val pan: String,
    val exNonce: String
): DTO