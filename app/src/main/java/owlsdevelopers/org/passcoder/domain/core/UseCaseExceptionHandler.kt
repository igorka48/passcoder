package owlsdevelopers.org.passcoder.domain.core

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

typealias UseCaseExceptionHandler = CoroutineExceptionHandler

fun UseCaseExceptionHandler(handler: (CoroutineContext, Throwable) -> Unit) = CoroutineExceptionHandler(handler)