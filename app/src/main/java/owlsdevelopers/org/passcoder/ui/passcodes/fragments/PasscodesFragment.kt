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
import owlsdevelopers.org.passcoder.ui.passcodes.adapters.PasscodeAdapter
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesListViewModel


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

        viewModel.loadIndicator.observe(this, Observer { value -> value?.let { swipeRefresh.isRefreshing = it } })
        viewModel.showActions.observe(this, Observer { value -> value?.let { showActionsDialog() } })

        viewModel.toastInfo.observe(this, Observer { value ->
            value?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
        initRecyclerView()
        swipeRefresh.setOnRefreshListener { viewModel.reloadData() }
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


}
