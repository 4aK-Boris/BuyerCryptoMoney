package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.IDData
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.IDDataModel

class IDDataMapper(
    private val merchantAcquirerIDMapper: MerchantAcquirerIDMapper,
    private val acquirerIDMapper: AcquirerIDMapper
) {

    fun map(model: IDDataModel?): IDData? {
        return model?.let {
            IDData(
                merchantAcquirerID = merchantAcquirerIDMapper.map(model = model.merchantAcquirerID),
                acquirerID = acquirerIDMapper.map(model = model.acquirerID)
            )
        }
    }

    fun map(dto: IDData?): IDDataModel? {
        return dto?.let {
            IDDataModel(
                merchantAcquirerID = merchantAcquirerIDMapper.map(dto = dto.merchantAcquirerID),
                acquirerID = acquirerIDMapper.map(dto = dto.acquirerID)
            )
        }
    }
}
