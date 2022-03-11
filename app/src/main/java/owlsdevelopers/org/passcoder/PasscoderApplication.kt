package owlsdevelopers.org.passcoder

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import owlsdevelopers.org.passcoder.domain.usecases.*
import owlsdevelopers.org.passcoder.presentation.di.dataModule
import owlsdevelopers.org.passcoder.presentation.di.useCaseModule
import owlsdevelopers.org.passcoder.presentation.di.viewModule

class PasscoderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PasscoderApplication)
            modules(listOf(dataModule, viewModule, useCaseModule))
        }
    }
}