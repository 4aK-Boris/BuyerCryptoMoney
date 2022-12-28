package aleksandr.fedotkin.set.protocol.domain.repositories.general

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageHeader
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import java.math.BigInteger

interface MessageHeaderRepository: BaseSetRepository<MessageHeaderModel, MessageHeader> {

    suspend fun create(rrpid: BigInteger): MessageHeaderModel
}