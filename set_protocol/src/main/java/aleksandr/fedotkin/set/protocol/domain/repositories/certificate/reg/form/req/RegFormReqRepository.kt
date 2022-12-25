package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel
import java.math.BigInteger
import kotlinx.serialization.KSerializer

interface RegFormReqRepository {

    val convertToModel: (RegFormReqData) -> RegFormReqDataModel

    val convertToDTO: (RegFormReqDataModel) -> RegFormReqData

    val serializer: KSerializer<RegFormReqData>

    suspend fun createRegFormReqDataModel(lidEE: BigInteger, lidCA: BigInteger): Pair<RegFormReqDataModel, BigInteger>
    suspend fun createCryptoDataModel(
        number: String,
        lidEE: BigInteger,
        lidCA: BigInteger,
        caeThumb: ByteArray
    ): Pair<CryptoDataModel, BigInteger>
}
