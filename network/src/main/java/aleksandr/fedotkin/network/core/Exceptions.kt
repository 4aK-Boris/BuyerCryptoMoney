package aleksandr.fedotkin.network.core

class BadRequest : BaseException(BAD_REQUEST)
class InternalServerError : BaseException(INTERNAL_SERVER_ERROR)
class NoInternet : BaseException(NO_INTERNET)
class UnknownNetworkException : BaseException(NETWORK_CALL_FAIL)
class InvalidFormatNumberCardException : BaseException(INVALID_FORMAT_NUMBER_CARD_EXCEPTION)
class InvalidFormatCvcException : BaseException(INVALID_FORMAT_CVC_EXCEPTION)
class InvalidFormatMonthException : BaseException(INVALID_FORMAT_MONTH_EXCEPTION)
class InvalidFormatYearException : BaseException(INVALID_FORMAT_YEAR_EXCEPTION)
class SignatureError: BaseException(SIGNATURE_ERROR)
class SetError: BaseException(SET_ERROR)
class UnknownRRPID: BaseException(UNKNOWN_RRPID)
class ThumbsMismatch: BaseException(THUMBS_MISMATCH)
class ChallengeMismatch: BaseException(CHALLENGE_MISMATCH)
