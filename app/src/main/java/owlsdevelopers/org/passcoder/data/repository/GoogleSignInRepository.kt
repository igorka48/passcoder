package owlsdevelopers.org.passcoder.data.repository

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.domain.models.Credential
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GoogleSignInRepository(val context: Context) {

    private val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    suspend fun getSignInIntent(): Intent {
        // Configure Google Sign In

        val mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        return mGoogleSignInClient.signInIntent
    }

    suspend fun signOut(): Boolean {
        return suspendCoroutine { continuation ->
            run {
                // Configure Google Sign In
                GoogleSignIn.getClient(context, googleSignInOptions).signOut()
                        .addOnSuccessListener { continuation.resume(true) }
                        .addOnCanceledListener { continuation.resume(false) }
            }
        }
    }

    suspend fun handleSignInResultIntent(intent: Intent): Credential {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        val account = task.getResult(ApiException::class.java)
        return Credential(account?.idToken ?: "")
    }
}