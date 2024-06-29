package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/**
 * The coRunCatching implementation
 * https://detekt.dev/docs/rules/coroutines/#suspendfunswallowedcancellation
 */
inline fun <T> CoroutineScope.coRunCatching(block: CoroutineScope.() -> T): Result<T> {
    return try {
        Result.success(value = block())
    } catch (e: Throwable) {
        ensureActive()
        Result.failure(exception = e)
    }
}

/**
 * Result:
 * Done!
 *
 * If `ensureActive()` is replaced by `throw e`, the output will be:
 * onFailure: kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled
 */
private suspend fun main(): Unit = coroutineScope {
    val job = launch {
        coRunCatching {
            // Delays this coroutine a little so that it does not complete before being cancelled.
            delay(timeMillis = 100)
            println("launched!")
        }.onSuccess {
            println("onSuccess: $it")
        }.onFailure {
            println("onFailure: $it")
        }
    }
    job.cancelAndJoin()
    println("Done!")
}
