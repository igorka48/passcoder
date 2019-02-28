package owlsdevelopers.org.passcoder.ui.passcodes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.ui.actions.ActionsFragment
import owlsdevelopers.org.passcoder.ui.core.ViewEvent
import owlsdevelopers.org.passcoder.ui.passcodes.adapters.PasscodeAdapter
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesListViewModel
import owlsdevelopers.org.passcoder.ui.util.bind


/**
 * A placeholder fragment containing a simple view.
 */
class PasscodesFragment : Fragment(), PasscodeAdapter.Callback {


    val viewModel by viewModel<PasscodesListViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showActions.observe(this, Observer { value -> value?.let { showActionsDialog() } })

        viewModel.viewEvent.observe(this, Observer {
            when(it){
                is ViewEvent.Error -> showError(it.message)
                is ViewEvent.Info -> showInfo(it.message)
                is ViewEvent.ShowLoading -> swipeRefresh.isRefreshing = true
                is ViewEvent.HideLoading -> swipeRefresh.isRefreshing = false
            }
        })
        initRecyclerView()
        swipeRefresh.bind { viewModel.reloadCommand() }
    }

    private fun initRecyclerView() {
        val adapter = PasscodeAdapter(this)
        recyclerView.adapter = adapter
        viewModel.livePagedList.observe(this, Observer<PagedList<Passcode>> {
            if (swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        })
    }
    private fun showActionsDialog() {
        val newFragment = ActionsFragment.newInstance()
        newFragment.isCancelable = true
        newFragment.show(fragmentManager, "dialog2")
    }

    override fun onItemClicked(item: Passcode) {
        viewModel.onItemClicked(item)
    }

    override fun onItemLongClicked(item: Passcode) {
       viewModel.onItemLongClicked(item)
    }

    private fun showError(message: String){
        Toast.makeText(context!!, message, Toast.LENGTH_LONG).show()
    }

    private fun showInfo(message: String){
        Toast.makeText(context!!, message, Toast.LENGTH_LONG).show()
    }
}
