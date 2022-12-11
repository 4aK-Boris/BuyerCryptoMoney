package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitReq
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitReqMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitReqRepository
import java.math.BigInteger
import kotlin.random.Random

class CardCInitReqRepositoryImpl(
    private val messageWrapperRepository: MessageWrapperRepository,
    private val cardCInitReqMapper: CardCInitReqMapper,
    private val jsonMapper: JsonMapper
) : CardCInitReqRepository {

    override suspend fun createCardCInitReqMessageWrapperJson(): String {
        return jsonMapper.objectToString(
            data = createCardCInitReqMessageWrapper(),
            serializer = MessageWrapper.serializer(CardCInitReq.serializer())
        )
    }

    override suspend fun createCardCInitReqMessageWrapper(): MessageWrapper<CardCInitReq> {
        return messageWrapperRepository.convertToDTO(
            messageWrapperModel = createCardCInitReqMessageWrapperModel(),
            map = ::convertToDTO
        )
    }

    override fun convertToDTO(cardCInitReqModel: CardCInitReqModel): CardCInitReq {
        return cardCInitReqMapper.map(model = cardCInitReqModel)
    }

    override fun convertToModel(cardCInitReq: CardCInitReq): CardCInitReqModel {
        return cardCInitReqMapper.map(dto = cardCInitReq)
    }

    private suspend fun createCardCInitReqMessageWrapperModel(): MessageWrapperModel<CardCInitReqModel> {
        return createCardCInitReqModel().run {
            messageWrapperRepository.createMessageWrapperModel(
                messageModel = this,
                rrpid = this.rrpID
            )
        }
    }

    private fun createCardCInitReqModel(): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            brandID = generateNewNumber(),
            thumbs = emptyList()
        )
    }

    private fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}
