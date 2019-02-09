package owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import owlsdevelopers.org.passcoder.domain.core.UseCaseExceptionHandler
import owlsdevelopers.org.passcoder.domain.models.AddCodeFormData
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.models.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.domain.usecases.AddPasscode
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent


class AddPasscodeViewModel constructor(val addPasscode: AddPasscode) : ViewModel() {

    private val mErrorMessage = SingleLiveEvent<String>()
    private val mLoadIndicator = SingleLiveEvent<Boolean>()
    private val mHideDialog = SingleLiveEvent<Boolean>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage

    val loadIndicator: LiveData<Boolean>
        get() = mLoadIndicator

    val hideDialog: LiveData<Boolean>
        get() = mHideDialog

    fun cancelButtonClicked() {
        mHideDialog.value = true
    }

    fun postButtonClicked(formData: AddCodeFormData) {
        postCode(formData)
    }


    private fun postCode(formData: AddCodeFormData) {
        val passcode = Passcode(formData.code, formData.description, 0, System.currentTimeMillis())
        val handler = UseCaseExceptionHandler { _, exception ->
            mErrorMessage.value = exception.localizedMessage
        }
        mLoadIndicator.value = true
        addPasscode(AddPasscode.Params(passcode), handler) { success ->
            if (success) {
                mHideDialog.value = true
                mLoadIndicator.value = false
            }
        }
    }
}