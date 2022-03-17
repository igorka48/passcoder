package owlsdevelopers.org.passcoder.ui.actions.fragments

import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.ActionsFragmentBinding
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.ui.actions.navigation.ActionsNavigationEvents
import owlsdevelopers.org.passcoder.ui.actions.navigation.navigationFun
import owlsdevelopers.org.passcoder.ui.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.ui.core.BasicFragment
import owlsdevelopers.org.passcoder.ui.util.FragmentArgumentDelegate

class ActionsFragment : BasicFragment<ActionsNavigationEvents>(R.layout.actions_fragment) {

    val viewModel by viewModel<ActionsViewModel>()

    override fun provideNavigationFunction() = navigationFun()
    override fun provideViewModel() = viewModel

    override fun initViews() {
        val viewBinding = ActionsFragmentBinding.bind(requireView())
    }

    companion object {
        fun newInstance(passcode: Passcode) = ActionsFragment().apply {
            passcodeValue = passcode.value
        }
    }

    private var passcodeValue by FragmentArgumentDelegate<String>()



}
