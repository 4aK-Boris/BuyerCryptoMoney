package aleksandr.fedotkin.buyercryptomoney.domain.model.set.general

import aleksandr.fedotkin.buyercryptomoney.core.Settings
import java.math.BigInteger
import java.time.LocalDateTime

data class MessageHeaderModel(
    val version: String = Settings.Version,
    val revision: String = Settings.revision,
    val messageIDModel: MessageIDModel?,
    val rrpId: BigInteger?,
    val sWIdent: String? = Settings.SWIdent,
    val date: LocalDateTime = LocalDateTime.now()
)
