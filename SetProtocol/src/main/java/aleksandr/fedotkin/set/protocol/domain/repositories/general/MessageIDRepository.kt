package aleksandr.fedotkin.set.protocol.domain.repositories.general

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageID
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel

interface MessageIDRepository: BaseSetRepository<MessageIDModel, MessageID> {

    suspend fun create(): MessageIDModel
}