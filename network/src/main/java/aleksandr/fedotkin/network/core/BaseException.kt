package aleksandr.fedotkin.network.core

open class BaseException(val extraErrorCode: Int, override val message: String? = null) :
    Exception(message)

