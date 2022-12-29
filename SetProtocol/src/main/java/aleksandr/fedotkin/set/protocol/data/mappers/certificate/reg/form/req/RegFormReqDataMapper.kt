package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import kotlinx.serialization.KSerializer

class RegFormReqDataMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val base64Mapper: Base64Mapper
) : SetMapper<RegFormReqDataModel, RegFormReqData> {

    override val serializer: KSerializer<RegFormReqData>
        get() = RegFormReqData.serializer()

    override fun map(value: RegFormReqDataModel): RegFormReqData {
        return RegFormReqData(
            rrpID = bigIntegerMapper.map(value = value.rrpID),
            lidEE = bigIntegerMapper.map(value = value.lidEE),
            challEE2 = bigIntegerMapper.map(value = value.challEE2),
            lidCA = bigIntegerMapper.map(value = value.lidCA),
            requestType = value.requestType,
            language = value.language,
            thumbs = value.thumbs.map { base64Mapper.map(value = it) }
        )
    }

    override fun reverseMap(value: RegFormReqData): RegFormReqDataModel {
        return RegFormReqDataModel(
            rrpID = bigIntegerMapper.reverseMap(value = value.rrpID),
            lidEE = bigIntegerMapper.reverseMap(value = value.lidEE),
            challEE2 = bigIntegerMapper.reverseMap(value = value.challEE2),
            lidCA = bigIntegerMapper.reverseMap(value = value.lidCA),
            requestType = value.requestType,
            language = value.language,
            thumbs = value.thumbs.map { base64Mapper.reverseMap(value = it) }
        )
    }
}
