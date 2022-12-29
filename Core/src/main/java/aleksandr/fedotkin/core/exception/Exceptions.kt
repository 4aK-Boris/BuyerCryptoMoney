package aleksandr.fedotkin.core.exception

class SafeCallFail : BaseException(extraErrorCode = SAFE_CALL_FAIL)
class BadRequest : BaseException(BAD_REQUEST)
class InternalServerError : BaseException(INTERNAL_SERVER_ERROR)
class NoInternet : BaseException(NO_INTERNET)
class UnknownNetworkException : BaseException(NETWORK_CALL_FAIL)
