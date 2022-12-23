package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel

interface RegFormResRepository {

    val convertToModel: (RegFormRes) -> RegFormResModel

    val convertToDTO: (RegFormResModel) -> RegFormRes

    suspend fun checkRegFormResModel(messageWrapperRegFormResModel: MessageWrapperModel<RegFormResModel>)
}