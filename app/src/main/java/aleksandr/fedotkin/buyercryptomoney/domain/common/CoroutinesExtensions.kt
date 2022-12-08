package aleksandr.fedotkin.buyercryptomoney.domain.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun <T> CoroutineScope.onDefaultAsync(call: suspend () -> T): Deferred<T> =
    async(Dispatchers.Default) { call() }

fun <T> CoroutineScope.onIOAsync(call: suspend () -> T): Deferred<T> =
    async(Dispatchers.IO) { call() }

fun <T> CoroutineScope.onIOLaunch(call: suspend () -> T) =
    launch(Dispatchers.IO) { call() }
