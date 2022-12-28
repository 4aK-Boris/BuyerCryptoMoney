package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageHeaderRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageIDRepository
import java.math.BigInteger

class MessageHeaderRepositoryImpl(
    override val mapper: MessageHeaderMapper,
    private val messageIDRepository: MessageIDRepository
) : MessageHeaderRepository {

    override suspend fun create(rrpid: BigInteger): MessageHeaderModel {
        return MessageHeaderModel(
            rrpId = rrpid, messageIDModel = messageIDRepository.create()
        )
    }
}