package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.CertReqData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.DateTimeMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.CertReqDataModel

class CertReqDataMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val dateTimeMapper: DateTimeMapper,
    private val idDataMapper: IDDataMapper,
    private val regFormItemsMapper: RegFormItemsMapper,
    private val caBackKeyDataMapper: CABackKeyDataMapper,
    private val publicKeySorEMapper: PublicKeySorEMapper,
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(model: CertReqDataModel): CertReqData {
        return CertReqData(
            rrpID = bigIntegerMapper.map(number = model.rrpID),
            lidEE = bigIntegerMapper.map(number = model.lidEE),
            challEE3 = bigIntegerMapper.map(number = model.challEE3),
            lidCA = bigIntegerMapper.map(number = model.lidCA),
            challCA = bigIntegerMapper.map(number = model.challCA),
            requestType = model.requestType,
            requestDate = dateTimeMapper.map(dateTime = model.requestDate),
            idData = idDataMapper.map(model = model.idData),
            regFormID = bigIntegerMapper.map(number = model.regFormID),
            regForm = model.regForm.map { regFormItemsMapper.map(model = it) },
            caBackKeyData = caBackKeyDataMapper.map(model = model.caBackKeyData),
            publicKeySorE = publicKeySorEMapper.map(model = model.publicKeySorE),
            eeThumb = byteArrayMapper.map(byteArray = model.eeThumb),
            thumbs = model.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }

    fun map(dto: CertReqData): CertReqDataModel {
        return CertReqDataModel(
            rrpID = bigIntegerMapper.map(string = dto.rrpID),
            lidEE = bigIntegerMapper.map(string = dto.lidEE),
            challEE3 = bigIntegerMapper.map(string = dto.challEE3),
            lidCA = bigIntegerMapper.map(string = dto.lidCA),
            challCA = bigIntegerMapper.map(string = dto.challCA),
            requestType = dto.requestType,
            requestDate = dateTimeMapper.map(dateTime = dto.requestDate),
            idData = idDataMapper.map(dto = dto.idData),
            regFormID = bigIntegerMapper.map(string = dto.regFormID),
            regForm = dto.regForm.map { regFormItemsMapper.map(dto = it) },
            caBackKeyData = caBackKeyDataMapper.map(dto = dto.caBackKeyData),
            publicKeySorE = publicKeySorEMapper.map(dto = dto.publicKeySorE),
            eeThumb = byteArrayMapper.map(string = dto.eeThumb),
            thumbs = dto.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }
}
