package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.BaseRepository
import aleksandr.fedotkin.set.protocol.data.dto.crypto.PANOnly
import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.PANOnlyMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import java.math.BigInteger

class PANOnlyRepositoryImpl(
    private val panOnlyMapper: PANOnlyMapper,
    private val jsonMapper: JsonMapper
) : PANOnlyRepository, BaseRepository() {

    override val serializer = PANOnly.serializer()

    override val convertToModel: (PANOnly) -> PANOnlyModel = panOnlyMapper::map

    override val convertToDTO: (PANOnlyModel) -> PANOnly = panOnlyMapper::map

    override suspend fun createPANOnlyModel(number: String): PANOnlyModel {
        return PANOnlyModel(
            pan = BigInteger(number),
            exNonce = generateNewNumber()
        )
    }
}
