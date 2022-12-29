package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageIDMapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageIDRepository

class MessageIDRepositoryImpl(override val mapper: MessageIDMapper) : MessageIDRepository {

    override suspend fun create(): MessageIDModel {
        return MessageIDModel(
            lIdC = generateNewNumber(),
            lIdM = generateNewNumber(),
            xId = generateNewNumber()
        )
    }
}