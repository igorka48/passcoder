package owlsdevelopers.org.passcoder.di
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import owlsdevelopers.org.passcoder.ui.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.ui.login.viewmodels.LoginViewModel
import owlsdevelopers.org.passcoder.ui.home.viewmodels.HomeViewModel
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesListViewModel

val viewModule = module {
    viewModel { PasscodesListViewModel(get(), get()) }
    viewModel { AddPasscodeViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ActionsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}