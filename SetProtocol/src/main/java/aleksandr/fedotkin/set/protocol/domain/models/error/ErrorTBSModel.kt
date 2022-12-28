package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import java.math.BigInteger

data class ErrorTBSModel<T: Model>(
    val errorCode: ErrorCode,
    val errorNonce: BigInteger,
    val errorOID: String?,
    val errorThumb: ByteArray,
    val errorMsg: ErrorMsgModel<T>
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ErrorTBSModel<*>

        if (errorCode != other.errorCode) return false
        if (errorNonce != other.errorNonce) return false
        if (errorOID != other.errorOID) return false
        if (!errorThumb.contentEquals(other.errorThumb)) return false
        if (errorMsg != other.errorMsg) return false

        return true
    }

    override fun hashCode(): Int {
        var result = errorCode.hashCode()
        result = 31 * result + errorNonce.hashCode()
        result = 31 * result + (errorOID?.hashCode() ?: 0)
        result = 31 * result + errorThumb.contentHashCode()
        result = 31 * result + errorMsg.hashCode()
        return result
    }

}
