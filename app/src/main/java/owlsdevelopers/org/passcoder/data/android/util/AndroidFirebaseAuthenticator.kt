package owlsdevelopers.org.passcoder.data.android.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CompletableDeferred


class AndroidFirebaseAuthenticator {
    private var userCompletableDeferred: CompletableDeferred<FirebaseUser?>? = null
    private val providers = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.AnonymousBuilder().build(),
        AuthUI.IdpConfig.AppleBuilder().build()
    )
    private var resultLauncher: ActivityResultLauncher<Intent>? = null

    fun bindFragment(fragment: Fragment){
         resultLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                Log.d("Tag", "SignIn success")

                if (result.resultCode == Activity.RESULT_OK) {
                    // There are no request codes
                    val data: Intent? = result.data
                    val response = IdpResponse.fromResultIntent(data)
                    val user = FirebaseAuth.getInstance().currentUser
                    userCompletableDeferred?.complete(user)
                } else {
                    userCompletableDeferred?.complete(null)
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...
                }
            }
    }

    suspend fun signIn(): FirebaseUser? {
        userCompletableDeferred = CompletableDeferred()
        resultLauncher?.launch(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .enableAnonymousUsersAutoUpgrade()
            .setAvailableProviders(providers)
            .build()) ?: throw Exception("You must bind Authenticator to Fragment first")

        return userCompletableDeferred?.await()
    }


}