package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.AcquirerID
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.AcquirerIDModel

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
