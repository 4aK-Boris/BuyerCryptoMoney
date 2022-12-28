package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.Model

data class RegFormResModel(
    val signature: ByteArray,
    val regFormResTBS: RegFormResTBSModel
) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegFormResModel

        if (!signature.contentEquals(other.signature)) return false
        if (regFormResTBS != other.regFormResTBS) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + regFormResTBS.hashCode()
        return result
    }
}
