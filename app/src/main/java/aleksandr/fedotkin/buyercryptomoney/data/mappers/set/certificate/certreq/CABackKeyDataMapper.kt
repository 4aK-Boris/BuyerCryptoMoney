package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.CABackKeyData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.CABackKeyDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository

class CABackKeyDataMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val keyRepository: KeyRepository
) {

    fun map(model: CABackKeyDataModel?): CABackKeyData? {
        return model?.let {
            CABackKeyData(
                caaIgId = model.caaIgId,
                caKey = byteArrayMapper.map(byteArray = model.caKey.encoded)
            )
        }
    }

    fun map(dto: CABackKeyData?): CABackKeyDataModel? {
        return dto?.let {
            CABackKeyDataModel(
                caaIgId = dto.caaIgId,
                caKey = keyRepository.decodeSecretKey(keyArray = byteArrayMapper.map(string = dto.caKey))
            )
        }
    }
}
