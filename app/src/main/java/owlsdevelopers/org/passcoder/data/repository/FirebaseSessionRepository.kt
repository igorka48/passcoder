package owlsdevelopers.org.passcoder.data.repository

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseSessionRepository(val context: Context) : SessionRepository {
    private val mAuth = FirebaseAuth.getInstance()

    override suspend fun createSession(): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getSession(): User? {
        val fbUser = mAuth.currentUser
        return if (fbUser != null) {
            User(fbUser.uid, fbUser.displayName ?: "", fbUser.photoUrl.toString())
        } else null
    }

    override suspend fun deleteSession(): Boolean {
        return suspendCoroutine { continuation ->
            run {
                FirebaseAuth.getInstance().signOut()
                // Configure Google Sign In
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(context.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                GoogleSignIn.getClient(context, gso).signOut()
                        .addOnSuccessListener { continuation.resume(true) }
                        .addOnCanceledListener { continuation.resume(false) }
            }
        }
    }
}