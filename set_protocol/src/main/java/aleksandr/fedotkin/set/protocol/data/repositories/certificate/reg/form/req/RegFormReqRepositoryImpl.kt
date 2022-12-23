package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.BaseRepository
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.certificate.RequestType
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EXHRepository
import java.math.BigInteger
import java.security.cert.X509Certificate

class RegFormReqRepositoryImpl(
    private val panOnlyRepository: PANOnlyRepository,
    private val exhRepository: EXHRepository,
    private val regFormReqDataMapper: RegFormReqDataMapper
) : RegFormReqRepository, BaseRepository() {

    override val serializer = RegFormReqData.serializer()

    override val convertToDTO: (RegFormReqDataModel) -> RegFormReqData = regFormReqDataMapper::map

    override val convertToModel: (RegFormReqData) -> RegFormReqDataModel = regFormReqDataMapper::map

    override suspend fun createRegFormReqDataModel(
        lidEE: BigInteger,
        lidCA: BigInteger
    ): Pair<RegFormReqDataModel, BigInteger> {
        return generateNewNumber().let { rrpid ->
            RegFormReqDataModel(
                rrpID = rrpid,
                lidEE = lidEE,
                challEE2 = generateNewNumber(),
                lidCA = lidCA,
                requestType = RequestType.SIGNATURE,
                language = Language.RUSSIAN,
                thumbs = emptyList()
            ) to rrpid
        }
    }

    override suspend fun createCryptoDataModel(
        number: String,
        lidEE: BigInteger,
        lidCA: BigInteger,
        certificate: X509Certificate
    ): Pair<CryptoDataModel, BigInteger> {
        return createRegFormReqDataModel(lidEE = lidEE, lidCA = lidCA)
            .let { (regFormReqDataModel, rrpid) ->
                exhRepository.encrypt(
                    publicKey = certificate.publicKey,
                    data = regFormReqDataModel,
                    secondaryData = panOnlyRepository.createPANOnlyModel(number = number),
                    map = convertToDTO,
                    secondaryMap = panOnlyRepository.convertToDTO,
                    serializer = serializer,
                    secondarySerializer = panOnlyRepository.serializer
                ) to rrpid
            }
    }
}
