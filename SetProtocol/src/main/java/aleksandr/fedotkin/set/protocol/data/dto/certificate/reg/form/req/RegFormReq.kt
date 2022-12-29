package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EXH
import kotlinx.serialization.Serializable

@Serializable
data class RegFormReq(val exh: EXH): DTO