package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import kotlinx.serialization.KSerializer

class RegFormResMapper(
    private val base64Mapper: Base64Mapper,
    private val regFormResTBSMapper: RegFormResTBSMapper
) : SetMapper<RegFormResModel, RegFormRes> {

    override val serializer: KSerializer<RegFormRes>
        get() = RegFormRes.serializer()

    override fun map(value: RegFormResModel): RegFormRes {
        return RegFormRes(
            signature = base64Mapper.map(value = value.signature),
            regFormResTBS = regFormResTBSMapper.map(value = value.regFormResTBS)
        )
    }

    override fun reverseMap(value: RegFormRes): RegFormResModel {
        return RegFormResModel(
            signature = base64Mapper.reverseMap(value = value.signature),
            regFormResTBS = regFormResTBSMapper.reverseMap(value = value.regFormResTBS)
        )
    }
}
