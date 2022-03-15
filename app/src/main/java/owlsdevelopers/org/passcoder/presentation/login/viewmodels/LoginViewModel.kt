package owlsdevelopers.org.passcoder.presentation.login.viewmodels

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.usecases.IsLogged
import owlsdevelopers.org.passcoder.domain.usecases.Login
import owlsdevelopers.org.passcoder.presentation.core.BasicViewModel
import owlsdevelopers.org.passcoder.presentation.core.LoadingEvent
import owlsdevelopers.org.passcoder.presentation.login.navigation.LoginNavigationEvents

class LoginViewModel constructor(val isLogged: IsLogged, val login: Login) : BasicViewModel() {

    init {
        checkIsLogged()
    }

    fun loginCommand(){
        doLogin()
    }

    private fun checkIsLogged(){
        mLoadingEvent.postValue(LoadingEvent.ShowLoading)
        isLogged(UseCase.None) { logged ->
            if(logged){
                mNavigationEvents.postValue(LoginNavigationEvents.MainScreen)
            }
            mLoadingEvent.postValue(LoadingEvent.HideLoading)
        }
    }

    private fun doLogin() {
        mLoadingEvent.postValue(LoadingEvent.ShowLoading)
        login(Login.Params(Credential("")), useCaseExceptionHandler) { user ->
            mLoadingEvent.postValue(LoadingEvent.HideLoading)
            checkIsLogged()
        }
    }
}