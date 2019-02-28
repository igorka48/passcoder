package owlsdevelopers.org.passcoder.ui.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent

abstract class BasicViewModel : ViewModel() {
    protected val mViewEvent = SingleLiveEvent<ViewEvent>()

    val viewEvent: LiveData<ViewEvent>
        get() = mViewEvent

}