package aleksandr.fedotkin.set.protocol.domain.models.general

import aleksandr.fedotkin.set.protocol.core.Settings
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger
import java.time.LocalDateTime

data class MessageHeaderModel(
    val version: String = Settings.VERSION,
    val revision: String = Settings.REVISION,
    val messageIDModel: MessageIDModel?,
    val rrpId: BigInteger?,
    val sWIdent: String? = Settings.SWIDENT,
    val date: LocalDateTime = LocalDateTime.now()
): Model
