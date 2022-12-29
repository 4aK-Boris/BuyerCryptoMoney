package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository

class CertStatusMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val caMsgMapper: CAMsgMapper,
    private val failedItemMapper: FailedItemMapper,
    private val keyRepository: KeyRepository
) {

//    fun map(model: CertStatusModel): CertStatus {
//        return CertStatus(
//            certStatusCode = model.certStatusCode,
//            nonceCCA = byteArrayMapper.map(byteArray = model.nonceCCA?.encoded),
//            eeMessage = model.eeMessage,
//            caMsg = caMsgMapper.map(model = model.caMsg),
//            failedItemSeq = model.failedItemSeq.map { failedItemMapper.map(model = it) }
//        )
//    }
//
//    fun map(dto: CertStatus): CertStatusModel {
//        return CertStatusModel(
//            certStatusCode = dto.certStatusCode,
//            nonceCCA = byteArrayMapper.map(string = dto.nonceCCA)
//                ?.let { keyRepository.decodeSecretKey(keyArray = it) },
//            eeMessage = dto.eeMessage,
//            caMsg = caMsgMapper.map(dto = dto.caMsg),
//            failedItemSeq = dto.failedItemSeq.map { failedItemMapper.map(dto = it) }
//        )
//    }
}
