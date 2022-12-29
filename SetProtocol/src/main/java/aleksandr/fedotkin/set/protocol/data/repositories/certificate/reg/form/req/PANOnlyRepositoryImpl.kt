package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.PANOnlyMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import java.math.BigInteger

class PANOnlyRepositoryImpl(
    override val mapper: PANOnlyMapper
) : PANOnlyRepository {

    override suspend fun create(number: String): PANOnlyModel {
        return PANOnlyModel(
            pan = BigInteger(number),
            exNonce = generateNewNumber()
        )
    }
}
