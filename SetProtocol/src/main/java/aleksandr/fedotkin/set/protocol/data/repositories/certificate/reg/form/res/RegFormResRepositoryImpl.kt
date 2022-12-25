package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormResTBS
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.SignatureRepository

class RegFormResRepositoryImpl(
    private val regFormResMapper: RegFormResMapper,
    private val signatureRepository: SignatureRepository,
    private val keyRepository: KeyRepository
) : RegFormResRepository {

    override val serializer = RegFormRes.serializer()

    override val convertToModel: (RegFormRes) -> RegFormResModel = regFormResMapper::map

    override val convertToDTO: (RegFormResModel) -> RegFormRes  = regFormResMapper::map

    override suspend fun checkSignature(regFormResModel: RegFormResModel): Boolean {
        return signatureRepository.verifySignature(
            data = convertToDTO(regFormResModel).regFormResTBS,
            serializer = RegFormResTBS.serializer(),
            publicKey = keyRepository.decodeCertificate(certificate = regFormResModel.regFormResTBS.caeThumb).publicKey,
            signatureArray = regFormResModel.ca
        )
    }
}