package owlsdevelopers.org.passcoder.presentation.home.viewmodels

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.usecases.Logout
import owlsdevelopers.org.passcoder.presentation.core.BasicViewModel
import owlsdevelopers.org.passcoder.presentation.core.LoadingEvent
import owlsdevelopers.org.passcoder.presentation.home.navigation.HomeNavigationEvents

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