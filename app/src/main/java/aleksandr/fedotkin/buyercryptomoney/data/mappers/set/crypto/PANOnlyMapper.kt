package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.PANOnly
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.PANOnlyModel

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