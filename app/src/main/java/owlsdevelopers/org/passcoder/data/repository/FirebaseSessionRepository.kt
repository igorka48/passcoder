package owlsdevelopers.org.passcoder.data.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import owlsdevelopers.org.passcoder.data.models.fromFirebase
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository
import owlsdevelopers.org.passcoder.ui.login.activities.LoginActivity
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseSessionRepository(val context: Context,
                                val googleSignInRepository: GoogleSignInRepository,
                                val firebaseAuth: FirebaseAuth) : SessionRepository {

    override suspend fun createSession(credential: Credential): User {
        return suspendCoroutine { continuation ->
            val googleCredential = GoogleAuthProvider.getCredential(credential.token, null)
            firebaseAuth.signInWithCredential(googleCredential)
                    .addOnSuccessListener {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(LoginActivity.TAG, "signInWithCredential:success")
                        firebaseAuth.currentUser?.let {
                            continuation.resume( User.fromFirebase(it))
                        }
                    }
                    .addOnFailureListener {
                        // If sign in fails, display a message to the user.
                        Log.w(LoginActivity.TAG, "signInWithCredential:failure", it)
                        continuation.resumeWithException(it)
                    }
        }
    }

    override suspend fun getSession(): User? {
        val fbUser = firebaseAuth.currentUser
        return if (fbUser != null) {
            User(fbUser.uid, fbUser.displayName ?: "", fbUser.photoUrl.toString())
        } else null
    }

    override suspend fun deleteSession(): Boolean {
        FirebaseAuth.getInstance().signOut()
        return googleSignInRepository.signOut()
    }
}