package aleksandr.fedotkin.set.protocol.core

open class BaseException(val extraErrorCode: Int, override val message: String? = null) :
    Exception(message)

