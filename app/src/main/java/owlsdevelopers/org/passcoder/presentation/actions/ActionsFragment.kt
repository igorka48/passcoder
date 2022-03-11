package owlsdevelopers.org.passcoder.presentation.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.presentation.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.presentation.util.FragmentArgumentDelegate

class ActionsFragment : DialogFragment() {

    companion object {
        fun newInstance(passcode: Passcode) = ActionsFragment().apply {
            passcodeValue = passcode.value
        }
    }

    private var passcodeValue by FragmentArgumentDelegate<String>()
    val viewModel by viewModel<AddPasscodeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.actions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
