package owlsdevelopers.org.passcoder.presentation.addpasscode.viewmodels

import androidx.lifecycle.LiveData
import owlsdevelopers.org.passcoder.domain.core.UseCaseExceptionHandler
import owlsdevelopers.org.passcoder.domain.models.AddCodeFormData
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.usecases.AddPasscode
import owlsdevelopers.org.passcoder.presentation.core.BasicViewModel
import owlsdevelopers.org.passcoder.presentation.core.ViewEvent
import owlsdevelopers.org.passcoder.presentation.util.SingleLiveEvent


class AddPasscodeViewModel constructor(val addPasscode: AddPasscode) : BasicViewModel() {

    private val mHideDialog = SingleLiveEvent<Boolean>()

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
//        val handler = UseCaseExceptionHandler { _, exception ->
//            mViewEvent.value = ViewEvent.Error(exception.localizedMessage)
//        }
//        mViewEvent.value = ViewEvent.ShowLoading
//        addPasscode(AddPasscode.Params(passcode), handler) { success ->
//            if (success) {
//                mHideDialog.value = true
//                mViewEvent.value = ViewEvent.HideLoading
//            }
//        }
    }
}