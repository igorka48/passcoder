package owlsdevelopers.org.passcoder.ui.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent

abstract class BasicViewModel : ViewModel() {
    private val mToastInfo = SingleLiveEvent<String>()
    private val mLoadIndicator = SingleLiveEvent<Boolean>()

    val toastInfo: LiveData<String>
        get() = mToastInfo

    val loadIndicator: LiveData<Boolean>
        get() = mLoadIndicator
}