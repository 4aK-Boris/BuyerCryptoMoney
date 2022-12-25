package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.PANOnly
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.PANOnlyModel

class PANOnlyMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(model: PANOnlyModel): PANOnly {
        return PANOnly(
            pan = bigIntegerMapper.map(number = model.pan),
            exNonce = bigIntegerMapper.map(number = model.exNonce)
        )
    }

    fun map(dto: PANOnly): PANOnlyModel {
        return PANOnlyModel(
            pan = bigIntegerMapper.map(string = dto.pan),
            exNonce = bigIntegerMapper.map(string  = dto.exNonce)
        )
    }
}