package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/**
 * This example demonstrates the behavior described below.
 * > if the coroutine in which await was called got cancelled,
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * try started!
 * async is running!
 * catch started!
 * Done!
 */
private suspend fun CoroutineScope.doExample1() {
    val job = launch {
        val deferred = async {
            println("async is running!")
        }
        try {
            println("try started!")
            deferred.await()
            println("try is ending!")
        } catch (e: CancellationException) {
            println("catch started!")
            currentCoroutineContext().ensureActive() // throws CancellationException because the current coroutine was cancelled.
            println(e) // executes.
            println("catch is ending!")
        }
    }
    job.cancelAndJoin()
    println("Done!")
}

/**
 * This example demonstrates the behavior described below.
 * > or if the Deferred itself got completed with a CancellationException.
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * async started!
 * catch started!
 * kotlinx.coroutines.JobCancellationException: DeferredCoroutine was cancelled; job=DeferredCoroutine{Cancelled}@c7ce68f
 * catch is ending!
 * Done!
 */
private suspend fun CoroutineScope.doExample2() {
    val deferred = async {
        println("async started!")
        // Delay this coroutine a little so that it does not complete before being cancelled.
        delay(100)
        println("async is ending!")
    }
    deferred.cancel()
    try {
        deferred.await()
        println("try is ending!")
    } catch (e: CancellationException) {
        println("catch started!")
        currentCoroutineContext().ensureActive() // does not throw CancellationException because the current coroutine was cancelled.
        println(e) // executes.
        println("catch is ending!")
    }
    println("Done!")
}

private suspend fun main() = coroutineScope {
    doExample1()
}
