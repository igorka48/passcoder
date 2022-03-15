package owlsdevelopers.org.passcoder.presentation.addpasscode.fragments

import android.view.View
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.FragmentAddPasscodeBinding
import owlsdevelopers.org.passcoder.domain.models.AddCodeFormData
import owlsdevelopers.org.passcoder.presentation.addpasscode.navigation.AddCodeNavigationEvents
import owlsdevelopers.org.passcoder.presentation.addpasscode.navigation.navigationFun
import owlsdevelopers.org.passcoder.presentation.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.presentation.core.*
import owlsdevelopers.org.passcoder.presentation.util.bind


class AddPasscodeFragment : BasicFragment<AddCodeNavigationEvents>(R.layout.fragment_add_passcode) {

    val viewModel by viewModel<AddPasscodeViewModel>()

    override fun provideNavigationFunction() = navigationFun()
    override fun provideViewModel() = viewModel

    override fun initViews() {
        val viewBinding = FragmentAddPasscodeBinding.bind(requireView())
        viewBinding.cancelButton.bind(viewModel::cancelCommand)
        viewBinding.postButton.bind(viewModel::postCommand)
        viewBinding.passcodeField.bind(viewModel.code)
        viewBinding.descriptionField.bind(viewModel.description)

        viewModel.loadingEvent.observe(this) {
            when (it) {
                is LoadingEvent.ShowLoading -> viewBinding.progressView.visibility = View.VISIBLE
                is LoadingEvent.HideLoading -> viewBinding.progressView.visibility = View.GONE
            }
        }
        viewModel.viewEvent.observe(this, Observer {
            when (it) {
                is ViewEvent.Error -> showError(it.exception.message)
                is ViewEvent.Info -> showInfo(it.message)
            }
        })
    }
}
