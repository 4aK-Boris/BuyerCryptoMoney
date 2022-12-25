package aleksandr.fedotkin.network.core.exception

class BadRequest : NetworkException(BAD_REQUEST)
class InternalServerError : NetworkException(INTERNAL_SERVER_ERROR)
class NoInternet : NetworkException(NO_INTERNET)
class UnknownNetworkException : NetworkException(NETWORK_CALL_FAIL)
