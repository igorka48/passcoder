package owlsdevelopers.org.passcoder.presentation.di
import org.koin.dsl.module
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import owlsdevelopers.org.passcoder.data.android.util.AndroidFirebaseAuthenticator
import owlsdevelopers.org.passcoder.data.repository.AndroidClipboardRepository
import owlsdevelopers.org.passcoder.data.repository.FirebasePasscodeRepository
import owlsdevelopers.org.passcoder.data.repository.FirebaseSessionRepository
import owlsdevelopers.org.passcoder.data.repository.GoogleSignInRepository
import owlsdevelopers.org.passcoder.domain.repository.ClipboardRepository
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository
import owlsdevelopers.org.passcoder.presentation.Constants.FIREBASE_DB_REFERENCE_NAME

val dataModule = module {
            single { FirebaseDatabase.getInstance().getReference(FIREBASE_DB_REFERENCE_NAME) }
            factory { FirebaseAuth.getInstance() }
            single<PasscodeRepository> { FirebasePasscodeRepository(get()) }
            single<ClipboardRepository> { AndroidClipboardRepository(get()) }
            single<SessionRepository> { FirebaseSessionRepository(get(), get(), get(), get()) }
            single{ GoogleSignInRepository(get()) }
            single { AndroidFirebaseAuthenticator() }
        }