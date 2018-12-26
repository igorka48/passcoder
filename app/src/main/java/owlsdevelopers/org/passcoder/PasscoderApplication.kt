package owlsdevelopers.org.passcoder

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import owlsdevelopers.org.passcoder.data.repository.AndroidClipboardRepository
import owlsdevelopers.org.passcoder.data.repository.FirebasePasscodeRepository
import owlsdevelopers.org.passcoder.model.repository.ClipboardRepository
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.ui.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesListViewModel

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
            viewModel { ActionsViewModel(get()) }

        }

        startKoin(this, listOf(dataModule, viewModule))
    }
}