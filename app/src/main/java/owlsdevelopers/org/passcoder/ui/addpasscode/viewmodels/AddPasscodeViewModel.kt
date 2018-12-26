package owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import owlsdevelopers.org.passcoder.model.AddCodeFormData
import owlsdevelopers.org.passcoder.model.Passcode
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent


class AddPasscodeViewModel constructor(val passcodeRepository: PasscodeRepository) : ViewModel() {

    private val mErrorMessage = SingleLiveEvent<String>()
    private val mLoadIndicator = SingleLiveEvent<Boolean>()
    private val mHideDialog = SingleLiveEvent<Boolean>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage

    val loadIndicator: LiveData<Boolean>
        get() = mLoadIndicator

    val hideDialog: LiveData<Boolean>
        get() = mHideDialog


    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = Job()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun cancelButtonClicked() {
        mHideDialog.value = true
    }

    fun postButtonClicked(formData: AddCodeFormData) {
        postCode(formData)
    }


    private fun postCode(formData: AddCodeFormData) {
        val passcode = Passcode(formData.code, formData.description, 0, System.currentTimeMillis())
        val handler = CoroutineExceptionHandler { _, exception ->
            mErrorMessage.value = exception.localizedMessage
        }
        uiScope.launch(handler) {
            val success = passcodeRepository.addPasscode(passcode)
            if (success) {
                mHideDialog.value = true
            }
        }
    }
}