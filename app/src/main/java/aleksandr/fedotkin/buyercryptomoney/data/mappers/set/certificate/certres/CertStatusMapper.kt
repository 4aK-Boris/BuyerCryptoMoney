package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres.CertStatus
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certres.CertStatusModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository

class CertStatusMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val caMsgMapper: CAMsgMapper,
    private val failedItemMapper: FailedItemMapper,
    private val keyRepository: KeyRepository
) {

    fun map(model: CertStatusModel): CertStatus {
        return CertStatus(
            certStatusCode = model.certStatusCode,
            nonceCCA = byteArrayMapper.map(byteArray = model.nonceCCA?.encoded),
            eeMessage = model.eeMessage,
            caMsg = caMsgMapper.map(model = model.caMsg),
            failedItemSeq = model.failedItemSeq.map { failedItemMapper.map(model = it) }
        )
    }

    fun map(dto: CertStatus): CertStatusModel {
        return CertStatusModel(
            certStatusCode = dto.certStatusCode,
            nonceCCA = byteArrayMapper.map(string = dto.nonceCCA)
                ?.let { keyRepository.decodeSecretKey(keyArray = it) },
            eeMessage = dto.eeMessage,
            caMsg = caMsgMapper.map(dto = dto.caMsg),
            failedItemSeq = dto.failedItemSeq.map { failedItemMapper.map(dto = it) }
        )
    }
}
