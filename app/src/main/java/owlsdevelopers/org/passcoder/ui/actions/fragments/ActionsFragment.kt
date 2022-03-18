package owlsdevelopers.org.passcoder.ui.actions.fragments

import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.ActionsFragmentBinding
import owlsdevelopers.org.passcoder.ui.actions.navigation.ActionsNavigationEvents
import owlsdevelopers.org.passcoder.ui.actions.navigation.navigationFun
import owlsdevelopers.org.passcoder.ui.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.ui.core.BasicFragment
import owlsdevelopers.org.passcoder.ui.util.bind

class ActionsFragment : BasicFragment<ActionsNavigationEvents>(R.layout.actions_fragment) {
    private val args: ActionsFragmentArgs by navArgs()
    private val viewModel:ActionsViewModel by viewModel{ parametersOf(args.passcode) }
    override fun provideNavigationFunction() = navigationFun()
    override fun provideViewModel() = viewModel

    override fun initViews() {
        val viewBinding = ActionsFragmentBinding.bind(requireView())
        with(viewBinding){
            viewModel.passcodeValue.observe(viewLifecycleOwner){
                titleLabel.text = it
            }
            alreadyRedeemedButton.bind(viewModel::alreadyRedeemedCommand)
            fullyRedeemedButton.bind(viewModel::fullyRedeemedCommand)
        }
    }
}
