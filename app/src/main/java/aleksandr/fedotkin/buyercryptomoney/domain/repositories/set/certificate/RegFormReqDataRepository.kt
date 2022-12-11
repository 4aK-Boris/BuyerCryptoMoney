package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RegFormReqData
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.RegFormReqDataModel
import java.math.BigInteger

interface RegFormReqDataRepository {

    suspend fun createRegFormReqDataModel(lidEE: BigInteger, lidCA: BigInteger): Pair<RegFormReqDataModel, BigInteger>

    suspend fun createAndConvertToByteArray(lidEE: BigInteger, lidCA: BigInteger): ByteArray

    suspend fun convertToByteArray(regFormReqDataModel: RegFormReqDataModel): ByteArray

    fun convertToModel(regFormReqData: RegFormReqData): RegFormReqDataModel

    fun convertToDTO(regFormReqDataModel: RegFormReqDataModel): RegFormReqData
}
