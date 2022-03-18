package owlsdevelopers.org.passcoder.ui.actions.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.usecases.MarkPasscodeAlreadyRedeemed
import owlsdevelopers.org.passcoder.domain.usecases.MarkPasscodeFullyRedeemed
import owlsdevelopers.org.passcoder.ui.actions.navigation.ActionsNavigationEvents
import owlsdevelopers.org.passcoder.ui.core.BasicViewModel

class ActionsViewModel constructor(
    private val passcode: Passcode,
    private val markPasscodeAlreadyRedeemed: MarkPasscodeAlreadyRedeemed,
    private val markPasscodeFullyRedeemed: MarkPasscodeFullyRedeemed
) : BasicViewModel() {
    val passcodeValue: LiveData<String> = MutableLiveData(passcode.value)

    fun alreadyRedeemedCommand(){
        markPasscodeAlreadyRedeemed(MarkPasscodeAlreadyRedeemed.Params(passcode)){
            mNavigationEvents.postValue(ActionsNavigationEvents.Back)
        }
    }
    fun fullyRedeemedCommand(){
        markPasscodeFullyRedeemed(MarkPasscodeFullyRedeemed.Params(passcode)){
            mNavigationEvents.postValue(ActionsNavigationEvents.Back)
        }
    }
}
