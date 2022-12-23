package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository

class RegFormResRepositoryImpl(
    private val regFormResMapper: RegFormResMapper
) : RegFormResRepository {

    override val convertToModel: (RegFormRes) -> RegFormResModel = regFormResMapper::map

    override val convertToDTO: (RegFormResModel) -> RegFormRes  = regFormResMapper::map

    override suspend fun checkRegFormResModel(messageWrapperRegFormResModel: MessageWrapperModel<RegFormResModel>) {

    }
}