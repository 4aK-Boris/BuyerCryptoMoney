package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres.FailedItem
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certres.FailedItemModel

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
