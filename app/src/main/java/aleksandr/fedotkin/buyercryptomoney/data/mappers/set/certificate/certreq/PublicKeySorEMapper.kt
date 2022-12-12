package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.PublicKeySorE
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.PublicKeySorEModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository

class PublicKeySorEMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val keyRepository: KeyRepository
) {

    fun map(model: PublicKeySorEModel): PublicKeySorE {
        return PublicKeySorE(
            publicKeyE = byteArrayMapper.map(byteArray = model.publicKeyE?.encoded),
            publicKeyS = byteArrayMapper.map(byteArray = model.publicKeyS?.encoded)
        )
    }

    fun map(dto: PublicKeySorE): PublicKeySorEModel {
        return PublicKeySorEModel(
            publicKeyE = dto.publicKeyE?.let {
                keyRepository.decodePublicKey(
                    array = byteArrayMapper.map(
                        string = it
                    )
                )
            },
            publicKeyS = dto.publicKeyS?.let {
                keyRepository.decodePublicKey(
                    array = byteArrayMapper.map(
                        string = it
                    )
                )
            }
        )
    }
}
