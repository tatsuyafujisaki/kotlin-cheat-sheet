package flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @see [https://github.com/Kotlin/kotlinx.coroutines/issues/1446#issuecomment-525565729]
 */
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect {
        val currentTime = System.currentTimeMillis()
        if (windowDuration < (currentTime - lastEmissionTime)) {
            lastEmissionTime = currentTime
            emit(it)
        }
    }
}
