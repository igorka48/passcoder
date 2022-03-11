package owlsdevelopers.org.passcoder.domain.core

import kotlinx.coroutines.*


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    protected var exceptionHandler: UseCaseExceptionHandler? = null
    private val uiScope =  CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val defaultScope = CoroutineScope( SupervisorJob() + Dispatchers.Default)

    operator fun invoke(params: Params, scope: CoroutineScope, onResult: (Type) -> Unit = {}) {
        val job = scope.async { run(params) }
        uiScope.launch{ onResult(job.await()) }
    }

    operator fun invoke(params: Params, scope: CoroutineScope, errorHandler: UseCaseExceptionHandler, onResult: (Type) -> Unit = {}) {
        exceptionHandler = errorHandler
        val job = scope.async { run(params) }
        uiScope.launch(errorHandler){ onResult(job.await()) }
    }

    operator fun invoke(params: Params, errorHandler: UseCaseExceptionHandler, onResult: (Type) -> Unit = {}) {
        exceptionHandler = errorHandler
        val job = defaultScope.async { run(params) }
        uiScope.launch(errorHandler) { onResult(job.await()) }
    }

    operator fun invoke(params: Params, onResult: (Type) -> Unit = {}) {
        val job = defaultScope.async { run(params) }
        uiScope.launch{ onResult(job.await()) }
    }


    object None
}
