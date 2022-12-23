package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel

class RegFormReqDataMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(model: RegFormReqDataModel): RegFormReqData {
        return RegFormReqData(
            rrpID = bigIntegerMapper.map(number = model.rrpID),
            lidEE = bigIntegerMapper.map(number = model.lidEE),
            challEE2 = bigIntegerMapper.map(number = model.challEE2),
            lidCA = bigIntegerMapper.map(number = model.lidCA),
            requestType = model.requestType,
            language = model.language,
            thumbs = model.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }

    fun map(dto: RegFormReqData): RegFormReqDataModel {
        return RegFormReqDataModel(
            rrpID = bigIntegerMapper.map(string = dto.rrpID),
            lidEE = bigIntegerMapper.map(string = dto.lidEE),
            challEE2 = bigIntegerMapper.map(string = dto.challEE2),
            lidCA = bigIntegerMapper.map(string =dto.lidCA),
            requestType = dto.requestType,
            language = dto.language,
            thumbs = dto.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }
}
