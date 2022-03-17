package owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels

import androidx.lifecycle.MutableLiveData
import owlsdevelopers.org.passcoder.domain.models.AddCodeFormData
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.usecases.AddPasscode
import owlsdevelopers.org.passcoder.ui.Constants.EMPTY_STRING
import owlsdevelopers.org.passcoder.ui.addpasscode.navigation.AddCodeNavigationEvents
import owlsdevelopers.org.passcoder.ui.core.BasicViewModel
import owlsdevelopers.org.passcoder.ui.core.LoadingEvent


class AddPasscodeViewModel constructor(val addPasscode: AddPasscode) : BasicViewModel() {
    val code = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    fun cancelCommand() {
        mNavigationEvents.postValue(AddCodeNavigationEvents.Back)
    }

    fun postCommand() {
        val formData = AddCodeFormData(
            code = code.value ?: EMPTY_STRING,
            description = description.value ?: EMPTY_STRING
        )
        postCode(formData)
    }

    private fun postCode(formData: AddCodeFormData) {
        val passcode = Passcode(
            value = formData.code,
            description = formData.description,
            additionTime = System.currentTimeMillis()
        )
        mLoadingEvent.postValue(LoadingEvent.ShowLoading)
        addPasscode(AddPasscode.Params(passcode), useCaseExceptionHandler) {
            mLoadingEvent.postValue(LoadingEvent.HideLoading)
            mNavigationEvents.postValue(AddCodeNavigationEvents.Back)
        }
    }
}