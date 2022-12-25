package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.MerchantAcquirerID
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.MerchantAcquirerIDModel

class MerchantAcquirerIDMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(model: MerchantAcquirerIDModel): MerchantAcquirerID {
        return MerchantAcquirerID(
            merchantBIN = bigIntegerMapper.map(number = model.merchantBIN),
            merchantID = bigIntegerMapper.map(number = model.merchantID)
        )
    }

    fun map(dto: MerchantAcquirerID): MerchantAcquirerIDModel {
        return MerchantAcquirerIDModel(
            merchantBIN = bigIntegerMapper.map(string = dto.merchantBIN),
            merchantID = bigIntegerMapper.map(string = dto.merchantID)
        )
    }
}
