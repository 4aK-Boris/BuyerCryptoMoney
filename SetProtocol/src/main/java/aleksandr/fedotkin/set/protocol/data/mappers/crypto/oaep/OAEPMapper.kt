package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEPModel
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import kotlinx.serialization.KSerializer

class OAEPMapper(
    private val base64Mapper: Base64Mapper,
    private val keyRepository: KeyRepository
) : SetMapper<OAEPModel, OAEP> {

    override val serializer: KSerializer<OAEP>
        get() = OAEP.serializer()

    override fun map(value: OAEPModel): OAEP {
        return OAEP(secretKey = base64Mapper.map(value = value.secretKey.encoded))
    }

    override fun reverseMap(value: OAEP): OAEPModel {
        return OAEPModel(
            secretKey = keyRepository.decodeSecretKey(
                keyArray = base64Mapper.reverseMap(
                    value = value.secretKey
                )
            )
        )
    }
}
