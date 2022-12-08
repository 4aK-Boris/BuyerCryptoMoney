package aleksandr.fedotkin.buyercryptomoney.domain.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

open class BaseUseCase {

    suspend fun <T : Any> safeCall(call: suspend CoroutineScope.() -> T): Result<T> = try {
        coroutineScope {
            Result.Success(call())
        }
    } catch (baseException: BaseException) {
        Result.Error(baseException, baseException.extraErrorCode)
    } catch (ex: Exception) {
        Result.Error(ex, SAFE_CALL_FAIL)
    }
}
