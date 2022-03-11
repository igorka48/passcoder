package owlsdevelopers.org.passcoder.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import owlsdevelopers.org.passcoder.data.android.util.AndroidFirebaseAuthenticator
import owlsdevelopers.org.passcoder.data.models.fromFirebase
import owlsdevelopers.org.passcoder.domain.errors.UserNotFoundError
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository

class FirebaseSessionRepository(val context: Context,
                                private val googleSignInRepository: GoogleSignInRepository,
                                private val firebaseAuth: FirebaseAuth,
                                private val androidFirebaseAuthenticator: AndroidFirebaseAuthenticator) : SessionRepository {

    override suspend fun createSession(credential: Credential): User {
        val fbUser = androidFirebaseAuthenticator.signIn()
        fbUser?.let {
          return  User.fromFirebase(it)
        } ?: throw UserNotFoundError()

//        return suspendCoroutine { continuation ->
//            val googleCredential = GoogleAuthProvider.getCredential(credential.token, null)
//            firebaseAuth.signInWithCredential(googleCredential)
//                    .addOnSuccessListener {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(MainActivity.TAG, "signInWithCredential:success")
//                        firebaseAuth.currentUser?.let {
//                            continuation.resume( User.fromFirebase(it))
//                        }
//                    }
//                    .addOnFailureListener {
//                        // If sign in fails, display a message to the user.
//                        Log.w(MainActivity.TAG, "signInWithCredential:failure", it)
//                        continuation.resumeWithException(it)
//                    }
//        }
    }

    override suspend fun getSession(): User? {
        val fbUser = firebaseAuth.currentUser
        return if (fbUser != null) {
            User(fbUser.uid, fbUser.displayName ?: "", fbUser.photoUrl.toString())
        } else null
    }

    override suspend fun deleteSession(): Boolean {
        FirebaseAuth.getInstance().signOut()
        //return googleSignInRepository.signOut()
        return true
    }
}