package owlsdevelopers.org.passcoder

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import owlsdevelopers.org.passcoder.data.repository.AndroidClipboardRepository
import owlsdevelopers.org.passcoder.data.repository.FirebasePasscodeRepository
import owlsdevelopers.org.passcoder.domain.repository.ClipboardRepository
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.domain.usecases.AddPasscode
import owlsdevelopers.org.passcoder.domain.usecases.GetPasscodes
import owlsdevelopers.org.passcoder.domain.usecases.Login
import owlsdevelopers.org.passcoder.domain.usecases.Logout
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
            single<PasscodeRepository> { FirebasePasscodeRepository(get()) }
            single<ClipboardRepository> { AndroidClipboardRepository(get()) }
        }

        val viewModule = module("view") {
            viewModel { PasscodesListViewModel(get(), get()) }
            viewModel { AddPasscodeViewModel(get()) }
            viewModel { PasscodesViewModel(get()) }
            viewModel { LoginViewModel(get()) }
            viewModel { ActionsViewModel(get()) }
        }

        val useCaseModule = module {
            single { AddPasscode(get()) }
            single { GetPasscodes(get()) }
            single { Login() }
            single { Logout() }
        }

        startKoin(this, listOf(dataModule, viewModule, useCaseModule))
    }
}