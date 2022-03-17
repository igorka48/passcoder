package owlsdevelopers.org.passcoder.presentation.actions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.ActionsFragmentBinding
import owlsdevelopers.org.passcoder.databinding.FragmentAddPasscodeBinding
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.presentation.actions.navigation.ActionsNavigationEvents
import owlsdevelopers.org.passcoder.presentation.actions.navigation.navigationFun
import owlsdevelopers.org.passcoder.presentation.actions.viewmodels.ActionsViewModel
import owlsdevelopers.org.passcoder.presentation.addpasscode.navigation.AddCodeNavigationEvents
import owlsdevelopers.org.passcoder.presentation.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.presentation.core.BasicFragment
import owlsdevelopers.org.passcoder.presentation.util.FragmentArgumentDelegate

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
