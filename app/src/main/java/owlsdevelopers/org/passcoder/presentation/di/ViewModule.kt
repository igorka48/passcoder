package owlsdevelopers.org.passcoder.presentation.di
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import owlsdevelopers.org.passcoder.presentation.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.presentation.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.presentation.login.viewmodels.LoginViewModel
import owlsdevelopers.org.passcoder.presentation.home.viewmodels.HomeViewModel
import owlsdevelopers.org.passcoder.presentation.passcodes.viewmodels.PasscodesListViewModel
import owlsdevelopers.org.passcoder.presentation.passcodes.viewmodels.PasscodesViewModel

val viewModule = module {
    viewModel { PasscodesListViewModel(get(), get()) }
    viewModel { AddPasscodeViewModel(get()) }
    viewModel { PasscodesViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ActionsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}