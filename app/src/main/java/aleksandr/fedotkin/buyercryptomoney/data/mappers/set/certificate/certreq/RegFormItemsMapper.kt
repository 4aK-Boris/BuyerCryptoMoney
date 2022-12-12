package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.RegFormItems
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.RegFormItemsModel

class RegFormItemsMapper {

    fun map(model: RegFormItemsModel): RegFormItems {
        return RegFormItems(fieldName = model.fieldName, fieldValue = model.fieldValue)
    }

    fun map(dto: RegFormItems): RegFormItemsModel {
        return RegFormItemsModel(fieldName = dto.fieldName, fieldValue = dto.fieldValue)
    }
}
