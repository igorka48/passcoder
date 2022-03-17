package owlsdevelopers.org.passcoder.ui.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import owlsdevelopers.org.passcoder.domain.core.UseCaseExceptionHandler
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent
import timber.log.Timber

abstract class BasicViewModel : ViewModel() {
    protected val mViewEvent = SingleLiveEvent<ViewEvent>()
    protected val mLoadingEvent = MutableLiveData<LoadingEvent>()

    val viewEvent: LiveData<ViewEvent>
        get() = mViewEvent

    val loadingEvent: LiveData<LoadingEvent>
        get() = mLoadingEvent

    protected val useCaseExceptionHandler = UseCaseExceptionHandler { _, t ->
        viewModelScope.launch(Dispatchers.Main) {
            mViewEvent.value = ViewEvent.Error(Exception(t.localizedMessage, t))
            mLoadingEvent.value = LoadingEvent.HideLoading
            Timber.e(t)
        }
    }

    protected val mNavigationEvents = SingleLiveEvent<NavigationEvents>()

    val navigationEvents: LiveData<NavigationEvents>
        get() = mNavigationEvents


}