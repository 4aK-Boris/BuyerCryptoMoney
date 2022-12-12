package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.IDData
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.IDDataModel

class IDDataMapper(
    private val merchantAcquirerIDMapper: MerchantAcquirerIDMapper,
    private val acquirerIDMapper: AcquirerIDMapper
) {

    @JvmName("map_model_notnull")
    fun map(model: IDDataModel): IDData {
        return IDData(
            merchantAcquirerID = merchantAcquirerIDMapper.map(model = model.merchantAcquirerID),
            acquirerID = acquirerIDMapper.map(model = model.acquirerID)
        )
    }

    @JvmName("map_dto_notnull")
    fun map(dto: IDData): IDDataModel {
        return IDDataModel(
            merchantAcquirerID = merchantAcquirerIDMapper.map(dto = dto.merchantAcquirerID),
            acquirerID = acquirerIDMapper.map(dto = dto.acquirerID)
        )
    }

    @JvmName("map_model_nullable")
    fun map(model: IDDataModel?): IDData? {
        return model?.let { map(model = it) }
    }

    @JvmName("map_dto_nullable")
    fun map(dto: IDData?): IDDataModel? {
        return dto?.let { map(dto = it) }
    }
}
