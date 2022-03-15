package owlsdevelopers.org.passcoder.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import owlsdevelopers.org.passcoder.data.android.util.AndroidFirebaseAuthenticator
import owlsdevelopers.org.passcoder.data.models.fromFirebase
import owlsdevelopers.org.passcoder.domain.errors.UserNotFoundError
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository

class FirebaseSessionRepository(
    val context: Context, private val firebaseAuth: FirebaseAuth,
    private val androidFirebaseAuthenticator: AndroidFirebaseAuthenticator
) : SessionRepository {

    override suspend fun createSession(credential: Credential): User {
        val fbUser = androidFirebaseAuthenticator.signIn()
        fbUser?.let {
            return User.fromFirebase(it)
        } ?: throw UserNotFoundError()
    }

    override suspend fun getSession(): User? {
        val fbUser = firebaseAuth.currentUser
        return if (fbUser != null) {
            User(fbUser.uid, fbUser.displayName ?: "", fbUser.photoUrl?.toString() ?: "")
        } else null
    }

    override suspend fun deleteSession(): Boolean {
        FirebaseAuth.getInstance().signOut()
        return true
    }
}