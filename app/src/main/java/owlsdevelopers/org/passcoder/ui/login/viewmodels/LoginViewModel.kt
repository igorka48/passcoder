package owlsdevelopers.org.passcoder.ui.login.viewmodels

import android.content.Intent
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import owlsdevelopers.org.passcoder.data.repository.GoogleSignInRepository
import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.core.UseCaseExceptionHandler
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.usecases.GetCurrentUser
import owlsdevelopers.org.passcoder.domain.usecases.Login
import owlsdevelopers.org.passcoder.ui.core.BasicViewModel
import owlsdevelopers.org.passcoder.ui.core.ViewEvent
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent

class LoginViewModel constructor(val login: Login, val getCurrentUser: GetCurrentUser, val googleSignInRepository: GoogleSignInRepository) : BasicViewModel() {

    private val mLoginIntent = SingleLiveEvent<Intent>()
    private val mUserData = SingleLiveEvent<User>()

    var googleSignInResultIntent = SingleLiveEvent<Intent>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    val loginIntent: LiveData<Intent>
        get() = mLoginIntent

    val userData: LiveData<User>
        get() = mUserData

    init {
        googleSignInResultIntent.observeForever {
            uiScope.launch {
                doLogin(googleSignInRepository.handleSignInResultIntent(it))
            }
        }
        getUser()
    }

    fun loginCommand(){
        showGoogleLoginScreen()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun showGoogleLoginScreen() {
        uiScope.launch {
            mLoginIntent.value = googleSignInRepository.getSignInIntent()
        }
    }

    private fun doLogin(credential: Credential) {
        val handler = UseCaseExceptionHandler { _, exception ->
            mViewEvent.value = ViewEvent.Error(exception.localizedMessage)
        }
        mViewEvent.value = ViewEvent.ShowLoading
        login(Login.Params(credential), handler) { user ->
            mViewEvent.value = ViewEvent.HideLoading
            mUserData.value = user
        }
    }

    private fun getUser() {
        val handler = UseCaseExceptionHandler { _, exception ->

        }
        mViewEvent.value = ViewEvent.ShowLoading
        getCurrentUser(UseCase.None(), handler) { user ->
            mViewEvent.value = ViewEvent.HideLoading
            mUserData.value = user
        }
    }
}