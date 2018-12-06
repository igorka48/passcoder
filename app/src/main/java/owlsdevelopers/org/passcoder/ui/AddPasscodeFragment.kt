package owlsdevelopers.org.passcoder.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_passcode.*
import kotlinx.coroutines.*
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.data.repository.FirebasePasscodeRepository
import owlsdevelopers.org.passcoder.model.Passcode


class AddPasscodeFragment : androidx.fragment.app.DialogFragment() {

    //TODO: Add depedency injection
    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("passcodes")
    private var passcodeRepository = FirebasePasscodeRepository(mDatabase)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_passcode, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelButton.setOnClickListener { hideDialog() }
        postButton.setOnClickListener { postCode() }
    }

    private fun postCode() {
        val passcode = Passcode(passcodeField.text.toString(), descriptionField.text.toString(), 0, System.currentTimeMillis())
        val handler = CoroutineExceptionHandler { _, exception ->
            showError(exception.localizedMessage)
        }
        GlobalScope.launch {
            val success = GlobalScope.async(handler) {
                passcodeRepository.addPasscode(passcode)
            }.await()
            if (success) {
                hideDialog()
            }
        }

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
