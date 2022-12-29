package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper

class CertResDataMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val certStatusMapper: CertStatusMapper,
    private val byteArrayMapper: ByteArrayMapper
) {

//    fun map(model: CertResDataModel): CertResData {
//        return CertResData(
//            rrpID = bigIntegerMapper.map(number = model.rrpID),
//            lidEE = bigIntegerMapper.map(number = model.lidEE),
//            challEE3 = bigIntegerMapper.map(number = model.challEE3),
//            lidCA = bigIntegerMapper.map(number = model.lidCA),
//            certStatus = certStatusMapper.map(model = model.certStatus),
//            certThumbs = model.certThumbs.map { byteArrayMapper.map(byteArray = it) },
//            brandCRLIdentifier = model.brandCRLIdentifier.map { byteArrayMapper.map(byteArray = it) },
//            thumbs = model.thumbs.map { byteArrayMapper.map(byteArray = it) }
//        )
//    }
//
//    fun map(dto: CertResData): CertResDataModel {
//        return CertResDataModel(
//            rrpID = bigIntegerMapper.map(string = dto.rrpID),
//            lidEE = bigIntegerMapper.map(string = dto.lidEE),
//            challEE3 = bigIntegerMapper.map(string = dto.challEE3),
//            lidCA = bigIntegerMapper.map(string = dto.lidCA),
//            certStatus = certStatusMapper.map(dto = dto.certStatus),
//            certThumbs = dto.certThumbs.map { byteArrayMapper.map(string = it) },
//            brandCRLIdentifier = dto.brandCRLIdentifier.map { byteArrayMapper.map(string = it) },
//            thumbs = dto.thumbs.map { byteArrayMapper.map(string = it) }
//        )
//    }
}
