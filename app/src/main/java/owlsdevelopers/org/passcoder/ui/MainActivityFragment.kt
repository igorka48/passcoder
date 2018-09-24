package owlsdevelopers.org.passcoder.ui

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import owlsdevelopers.org.passcoder.R

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCodeFab.setOnClickListener { showAddCodeDialog() }
    }

    private fun showAddCodeDialog() {
        val newFragment = AddPasscodeFragment.newInstance()
        newFragment.isCancelable = false
        newFragment.show(childFragmentManager, "dialog")
    }
}
