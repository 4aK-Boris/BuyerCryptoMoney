package aleksandr.fedotkin.buyercryptomoney.domain.common.data

open class BaseException(val extraErrorCode: Int, override val message: String? = null) :
    Exception(message)

// UseCase
const val SAFE_CALL_FAIL = -1

// Network
const val NETWORK_CALL_FAIL: Int = -2

const val BAD_REQUEST: Int = 400
const val UNAUTHORIZED: Int = 401
const val INTERNAL_SERVER_ERROR: Int = 500
const val NO_INTERNET: Int = 666 // (:

class BadRequest : BaseException(BAD_REQUEST)
class Unauthorized : BaseException(UNAUTHORIZED)
class InternalServerError : BaseException(INTERNAL_SERVER_ERROR)
class NoInternet : BaseException(NO_INTERNET)
class UnknownNetworkException : BaseException(NETWORK_CALL_FAIL)

// File IO
const val ON_SUCH_ELEMENT: Int = 3
const val UNKNOWN_IO_EXCEPTION: Int = 4

class NoSuchElement : BaseException(ON_SUCH_ELEMENT)
class UnknownIOException : BaseException(UNKNOWN_IO_EXCEPTION)

const val INVALID_FORMAT_MONTH_EXCEPTION = 555
const val INVALID_FORMAT_YEAR_EXCEPTION = 556
const val INVALID_FORMAT_CVC_EXCEPTION = 557
const val INVALID_FORMAT_NUMBER_CARD_EXCEPTION = 558

class InvalidFormatMonthException(message: String) : BaseException(INVALID_FORMAT_MONTH_EXCEPTION)
class InvalidFormatYearException(message: String) : BaseException(INVALID_FORMAT_YEAR_EXCEPTION)
class InvalidFormatCVCException(message: String) : BaseException(INVALID_FORMAT_CVC_EXCEPTION)
class InvalidFormatNumberCardException(message: String) :
    BaseException(INVALID_FORMAT_NUMBER_CARD_EXCEPTION)

// handle not implemented exceptions.
// no need of live code. just for test project
const val TODO_EXCEPTION = 0

class VRTODO : BaseException(TODO_EXCEPTION)

fun todoCrash(): Nothing = throw VRTODO()
