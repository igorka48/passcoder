package owlsdevelopers.org.passcoder.presentation.login.fragments

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.data.android.util.AndroidFirebaseAuthenticator
import owlsdevelopers.org.passcoder.databinding.FragmentLoginBinding
import owlsdevelopers.org.passcoder.presentation.core.BasicFragment
import owlsdevelopers.org.passcoder.presentation.core.LoadingEvent
import owlsdevelopers.org.passcoder.presentation.login.navigation.LoginNavigationEvents
import owlsdevelopers.org.passcoder.presentation.login.navigation.navigationFun
import owlsdevelopers.org.passcoder.presentation.login.viewmodels.LoginViewModel

class LoginFragment : BasicFragment<LoginNavigationEvents>(R.layout.fragment_login) {

    private val androidFirebaseAuthenticator: AndroidFirebaseAuthenticator by inject()

    private val viewModel by viewModel<LoginViewModel>()
    override fun provideViewModel() = viewModel
    override fun provideNavigationFunction() = navigationFun()

    override fun initViews() {
        val viewBinding = FragmentLoginBinding.bind(requireView())
        androidFirebaseAuthenticator.bindFragment(this)
        viewBinding.loginButton.setOnClickListener {viewModel.loginCommand()}
        viewBinding.shimmer.stopShimmer()
        viewModel.loadingEvent.observe(this, {
            when(it){
                is LoadingEvent.ShowLoading -> {
                    viewBinding.loginButton.isEnabled = false
                    viewBinding.shimmer.startShimmer()
                }
                is LoadingEvent.HideLoading -> {
                    viewBinding.loginButton.isEnabled = true
                    viewBinding.shimmer.stopShimmer()
                }
            }
        })
    }


}