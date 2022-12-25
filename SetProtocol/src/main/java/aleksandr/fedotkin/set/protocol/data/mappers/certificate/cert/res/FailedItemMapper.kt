package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.FailedItem
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.FailedItemModel

class FailedItemMapper {

    fun map(model: FailedItemModel): FailedItem {
        return FailedItem(
            itemNumber = model.itemNumber,
            itemReason = model.itemReason
        )
    }

    fun map(dto: FailedItem): FailedItemModel {
        return FailedItemModel(
            itemNumber = dto.itemNumber,
            itemReason = dto.itemReason
        )
    }
}
