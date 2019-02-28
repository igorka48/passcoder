package owlsdevelopers.org.passcoder.ui.addpasscode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_add_passcode.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.domain.models.AddCodeFormData
import owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels.AddPasscodeViewModel
import owlsdevelopers.org.passcoder.ui.core.ViewEvent
import java.lang.Error


class AddPasscodeFragment : DialogFragment() {

    val viewModel by viewModel<AddPasscodeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_passcode, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelButton.setOnClickListener { viewModel.cancelButtonClicked() }
        postButton.setOnClickListener {
            viewModel.postButtonClicked(AddCodeFormData(
                    code = passcodeField.text.toString(),
                    description = descriptionField.text.toString()))
        }
        viewModel.viewEvent.observe(this, Observer {
            when(it){
               is ViewEvent.Error -> showError(it.message)
                is ViewEvent.Info -> showError(it.message)
                is ViewEvent.ShowLoading -> progressView.visibility = VISIBLE
                is ViewEvent.HideLoading -> progressView.visibility = VISIBLE
            }
        })
        viewModel.hideDialog.observe(this, Observer { value ->
            value?.let {
                if (it) hideDialog()
            }
        })
    }


    private fun showError(localizedMessage: String?) {
        Toast.makeText(context, localizedMessage, Toast.LENGTH_LONG).show()
        Log.d("passcoder", localizedMessage)
    }

    private fun hideDialog() {
        this.dismiss()
    }


    companion object {
        fun newInstance(): AddPasscodeFragment {
            val fragment = AddPasscodeFragment()
            return fragment
        }
    }
}
