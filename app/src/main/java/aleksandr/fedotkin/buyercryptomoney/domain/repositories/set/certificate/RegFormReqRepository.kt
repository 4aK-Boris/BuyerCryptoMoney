package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import java.math.BigInteger
import java.security.PublicKey

interface RegFormReqRepository {

    suspend fun createMessageWrapperJson(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): String

    suspend fun createMessageWrapper(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): MessageWrapper<CryptoData>

    suspend fun createMessageWrapperModel(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): MessageWrapperModel<CryptoDataModel>

    suspend fun createCryptoDataModel(
        number: String,
        lidEE: BigInteger,
        lidCA: BigInteger,
        publicKey: PublicKey
    ): Pair<CryptoDataModel, BigInteger>
}
