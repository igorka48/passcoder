package owlsdevelopers.org.passcoder.ui.home.viewmodels

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.usecases.Logout
import owlsdevelopers.org.passcoder.ui.core.BasicViewModel
import owlsdevelopers.org.passcoder.ui.core.LoadingEvent
import owlsdevelopers.org.passcoder.ui.home.navigation.HomeNavigationEvents

class HomeViewModel constructor(val logout: Logout) : BasicViewModel() {

    fun addCodeCommand() {
        mNavigationEvents.postValue(HomeNavigationEvents.AddCode)
    }

    fun logoutCommand() {
        mLoadingEvent.postValue(LoadingEvent.ShowLoading)
        logout(UseCase.None) {  successLoggedOut ->
            mLoadingEvent.postValue(LoadingEvent.HideLoading)
            if(successLoggedOut){
                mNavigationEvents.postValue(HomeNavigationEvents.LoginScreen)
            }
        }
    }


}