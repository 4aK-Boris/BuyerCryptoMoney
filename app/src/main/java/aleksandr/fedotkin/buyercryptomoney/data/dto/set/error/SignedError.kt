package aleksandr.fedotkin.buyercryptomoney.data.dto.set.error

import kotlinx.serialization.Serializable

@Serializable
data class SignedError(val signature: String)