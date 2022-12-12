package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.MerchantAcquirerID
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.MerchantAcquirerIDModel

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
