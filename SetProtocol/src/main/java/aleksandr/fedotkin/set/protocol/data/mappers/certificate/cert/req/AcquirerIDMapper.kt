package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.AcquirerID
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.AcquirerIDModel

class AcquirerIDMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(model: AcquirerIDModel): AcquirerID {
        return AcquirerID(
            acquirerBIN = bigIntegerMapper.map(number = model.acquirerBIN),
            acquirerBusinessID = bigIntegerMapper.map(number = model.acquirerBusinessID)
        )
    }

    fun map(dto: AcquirerID): AcquirerIDModel {
        return AcquirerIDModel(
            acquirerBIN = bigIntegerMapper.map(string = dto.acquirerBIN),
            acquirerBusinessID = bigIntegerMapper.map(string = dto.acquirerBusinessID)
        )
    }
}
