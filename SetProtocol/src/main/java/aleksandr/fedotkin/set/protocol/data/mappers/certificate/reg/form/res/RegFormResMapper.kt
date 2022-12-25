package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel

class RegFormResMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val regFormResTBSMapper: RegFormResTBSMapper
) {

    fun map(model: RegFormResModel): RegFormRes {
        return RegFormRes(
            ca = byteArrayMapper.map(byteArray = model.ca),
            regFormResTBS = regFormResTBSMapper.map(model = model.regFormResTBS)
        )
    }

    fun map(dto: RegFormRes): RegFormResModel {
        return RegFormResModel(
            ca = byteArrayMapper.map(string = dto.ca),
            regFormResTBS = regFormResTBSMapper.map(model = dto.regFormResTBS)
        )
    }
}
