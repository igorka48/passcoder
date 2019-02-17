package owlsdevelopers.org.passcoder.data.models

import com.google.firebase.auth.FirebaseUser
import owlsdevelopers.org.passcoder.domain.models.User

fun User.Companion.fromFirebase(firebaseUser: FirebaseUser)
        = User(id = firebaseUser.uid,
        userName = firebaseUser.displayName ?: "",
        userPhoto = firebaseUser.photoUrl?.path ?: "")