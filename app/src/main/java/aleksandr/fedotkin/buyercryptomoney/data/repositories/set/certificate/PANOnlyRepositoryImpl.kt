package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.PANOnly
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto.PANOnlyMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.PANOnlyModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.PANOnlyRepository
import java.math.BigInteger
import kotlin.random.Random

class PANOnlyRepositoryImpl(
    private val panOnlyMapper: PANOnlyMapper,
    private val jsonMapper: JsonMapper
) : PANOnlyRepository {

    override fun convertToModel(panOnly: PANOnly): PANOnlyModel {
        return panOnlyMapper.map(dto = panOnly)
    }

    override fun convertToDTO(panOnlyModel: PANOnlyModel): PANOnly {
        return panOnlyMapper.map(model = panOnlyModel)
    }

    override suspend fun createPANOnlyModel(number: String): PANOnlyModel {
        return PANOnlyModel(
            pan = BigInteger(number),
            exNonce = generateNewNumber()
        )
    }

    override suspend fun convertToByteArray(panOnlyModel: PANOnlyModel): ByteArray {
        return jsonMapper.objectToByteArray(
            data = convertToDTO(panOnlyModel = panOnlyModel),
            serializer = PANOnly.serializer()
        )
    }

    private fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}
