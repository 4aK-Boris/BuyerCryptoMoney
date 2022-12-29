package aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.Model

data class CardCInitResModel(
    val signature: ByteArray,
    val cardCInitResTBS: CardCInitResTBSModel
) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardCInitResModel

        if (!signature.contentEquals(other.signature)) return false
        if (cardCInitResTBS != other.cardCInitResTBS) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + cardCInitResTBS.hashCode()
        return result
    }
}