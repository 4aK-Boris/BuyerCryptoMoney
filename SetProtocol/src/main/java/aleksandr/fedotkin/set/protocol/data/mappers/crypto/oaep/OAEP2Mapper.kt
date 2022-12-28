package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP2
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP2Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import kotlinx.serialization.KSerializer

class OAEP2Mapper(
    private val base64Mapper: Base64Mapper,
    private val keyRepository: KeyRepository
) : SetMapper<OAEP2Model, OAEP2> {

    override val serializer: KSerializer<OAEP2>
        get() = OAEP2.serializer()

    override fun map(value: OAEP2Model): OAEP2 {
        return OAEP2(
            secretKey = base64Mapper.map(value = value.secretKey.encoded),
            hash = base64Mapper.map(value = value.hash)
        )
    }

    override fun reverseMap(value: OAEP2): OAEP2Model {
        return OAEP2Model(
            secretKey = keyRepository.decodeSecretKey(
                keyArray = base64Mapper.reverseMap(
                    value = value.secretKey
                )
            ), hash = base64Mapper.reverseMap(value = value.hash)
        )
    }
}