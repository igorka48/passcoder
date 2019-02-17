package owlsdevelopers.org.passcoder

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import owlsdevelopers.org.passcoder.data.repository.AndroidClipboardRepository
import owlsdevelopers.org.passcoder.data.repository.FirebasePasscodeRepository
import owlsdevelopers.org.passcoder.data.repository.FirebaseSessionRepository
import owlsdevelopers.org.passcoder.data.repository.GoogleSignInRepository
import owlsdevelopers.org.passcoder.domain.repository.ClipboardRepository
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository
import owlsdevelopers.org.passcoder.domain.usecases.*
import owlsdevelopers.org.passcoder.ui.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.ui.login.viewmodels.LoginViewModel
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesListViewModel
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesViewModel

class PasscoderApplication : Application() {

    companion object {
        const val FIREBASE_DB_REFERENCE_NAME = "passcodes"
    }

    override fun onCreate() {
        super.onCreate()


        //DI
        val dataModule = module {
            single { FirebaseDatabase.getInstance().getReference(FIREBASE_DB_REFERENCE_NAME) }
            single { FirebaseAuth.getInstance() }
            single<PasscodeRepository> { FirebasePasscodeRepository(get()) }
            single<ClipboardRepository> { AndroidClipboardRepository(get()) }
            single<SessionRepository> { FirebaseSessionRepository(get(), get(), get()) }
            single{ GoogleSignInRepository(get()) }

        }

        val viewModule = module("view") {
            viewModel { PasscodesListViewModel(get(), get()) }
            viewModel { AddPasscodeViewModel(get()) }
            viewModel { PasscodesViewModel(get()) }
            viewModel { LoginViewModel(get(), get(), get()) }
            viewModel { ActionsViewModel(get()) }
        }

        val useCaseModule = module {
            single { AddPasscode(get()) }
            single { GetPasscodes(get()) }
            single { Login(get()) }
            single { GetCurrentUser(get()) }
            single { Logout() }
        }

        startKoin(this, listOf(dataModule, viewModule, useCaseModule))
    }
}