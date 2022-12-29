package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.PublicKeySorE
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.PublicKeySorEModel
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository

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
