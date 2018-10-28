package owlsdevelopers.org.passcoder.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import owlsdevelopers.org.passcoder.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_passcode.*
import owlsdevelopers.org.passcoder.model.Passcode


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddPasscodeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddPasscodeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPasscodeFragment : androidx.fragment.app.DialogFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("passcodes");


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }

    }

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
        mDatabase.setValue(passcode).addOnCompleteListener {
            t -> if(t.isSuccessful) hideDialog() else showError(t.exception?.localizedMessage)
        }
    }

    private fun showError(localizedMessage: String?) {
        Toast.makeText(context, localizedMessage, Toast.LENGTH_LONG).show()
        Log.d("passcoder", localizedMessage)
    }

    private fun hideDialog() {
        this.dismiss()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            mListener = context
//        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddPasscodeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): AddPasscodeFragment {
            val fragment = AddPasscodeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
