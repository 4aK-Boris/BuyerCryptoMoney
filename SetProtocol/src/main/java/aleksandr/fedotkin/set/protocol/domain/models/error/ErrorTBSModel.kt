package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger

data class ErrorTBSModel<T>(
    val errorCode: ErrorCode,
    val errorNonce: BigInteger,
    val errorOID: String?,
    val errorThumb: ByteArray?,
    val errorMsgModel: ErrorMsgModel<T>
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ErrorTBSModel<*>

        if (errorCode != other.errorCode) return false
        if (errorNonce != other.errorNonce) return false
        if (errorOID != other.errorOID) return false
        if (errorThumb != null) {
            if (other.errorThumb == null) return false
            if (!errorThumb.contentEquals(other.errorThumb)) return false
        } else if (other.errorThumb != null) return false
        if (errorMsgModel != other.errorMsgModel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = errorCode.hashCode()
        result = 31 * result + errorNonce.hashCode()
        result = 31 * result + (errorOID?.hashCode() ?: 0)
        result = 31 * result + (errorThumb?.contentHashCode() ?: 0)
        result = 31 * result + errorMsgModel.hashCode()
        return result
    }

}