package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.Language
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RegFormReqData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RequestType
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.RegFormReqDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.RegFormReqDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.RegFormReqDataRepository
import java.math.BigInteger
import kotlin.random.Random

class RegFormReqDataRepositoryImpl(
    private val regFormReqDataMapper: RegFormReqDataMapper,
    private val jsonMapper: JsonMapper
) : RegFormReqDataRepository {

    override suspend fun createRegFormReqDataModel(
        lidEE: BigInteger,
        lidCA: BigInteger
    ): RegFormReqDataModel {
        return RegFormReqDataModel(
            rrpID = generateNewNumber(),
            lidEE = lidEE,
            challEE2 = generateNewNumber(),
            lidCA = lidCA,
            requestType = RequestType.SIGNATURE,
            language = Language.RUSSIAN,
            thumbs = emptyList()
        )
    }

    override suspend fun convertToByteArray(regFormReqDataModel: RegFormReqDataModel): ByteArray {
        return jsonMapper.objectToByteArray(
            data = convertToDTO(regFormReqDataModel = regFormReqDataModel),
            serializer = RegFormReqData.serializer()
        )
    }

    override fun convertToModel(regFormReqData: RegFormReqData): RegFormReqDataModel {
        return regFormReqDataMapper.map(dto = regFormReqData)
    }

    override fun convertToDTO(regFormReqDataModel: RegFormReqDataModel): RegFormReqData {
        return regFormReqDataMapper.map(model = regFormReqDataModel)
    }

    private fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}
