package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.RegFormItems
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.RegFormItemsModel

class RegFormItemsMapper {

    fun map(model: RegFormItemsModel): RegFormItems {
        return RegFormItems(fieldName = model.fieldName, fieldValue = model.fieldValue)
    }

    fun map(dto: RegFormItems): RegFormItemsModel {
        return RegFormItemsModel(fieldName = dto.fieldName, fieldValue = dto.fieldValue)
    }
}
