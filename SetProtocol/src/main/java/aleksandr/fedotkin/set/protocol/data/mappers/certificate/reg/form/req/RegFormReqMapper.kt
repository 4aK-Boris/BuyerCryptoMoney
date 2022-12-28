package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReq
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EXHMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import kotlinx.serialization.KSerializer

class RegFormReqMapper(
    private val exhMapper: EXHMapper
): SetMapper<RegFormReqModel, RegFormReq> {

    override val serializer: KSerializer<RegFormReq>
        get() = RegFormReq.serializer()

    override fun map(value: RegFormReqModel): RegFormReq {
        return RegFormReq(exh = exhMapper.map(value = value.exh))
    }

    override fun reverseMap(value: RegFormReq): RegFormReqModel {
        return RegFormReqModel(exh = exhMapper.reverseMap(value = value.exh))
    }
}