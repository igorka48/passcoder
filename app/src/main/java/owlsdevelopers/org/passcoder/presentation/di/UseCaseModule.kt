package owlsdevelopers.org.passcoder.presentation.di
import org.koin.dsl.module
import owlsdevelopers.org.passcoder.domain.usecases.*

val useCaseModule = module {
    single { AddPasscode(get()) }
    single { GetPasscodes(get()) }
    single { Login(get()) }
    single { GetCurrentUser(get()) }
    single { Logout(get()) }
    single { IsLogged(get()) }
}