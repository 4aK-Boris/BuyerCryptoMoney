package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RegFormReqData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.RegFormReqDataModel

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
