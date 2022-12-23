package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.IDData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.IDDataModel

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
