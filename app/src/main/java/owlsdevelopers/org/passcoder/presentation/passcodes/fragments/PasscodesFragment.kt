package owlsdevelopers.org.passcoder.presentation.passcodes.fragments

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
import owlsdevelopers.org.passcoder.presentation.actions.ActionsFragment
import owlsdevelopers.org.passcoder.presentation.passcodes.adapters.PasscodeAdapter
import owlsdevelopers.org.passcoder.presentation.passcodes.viewmodels.PasscodesListViewModel
import owlsdevelopers.org.passcoder.presentation.util.bind


class PasscodesFragment : Fragment(), PasscodeAdapter.Callback {


    val viewModel by viewModel<PasscodesListViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showActions.observe(viewLifecycleOwner, Observer { value -> value?.let { showActionsDialog() } })

        viewModel.viewEvent.observe(viewLifecycleOwner, {
//            when(it){
//                is ViewEvent.Error -> showError(it.message)
//                is ViewEvent.Info -> showInfo(it.message)
//                is ViewEvent.ShowLoading -> swipeRefresh.isRefreshing = true
//                is ViewEvent.HideLoading -> swipeRefresh.isRefreshing = false
//            }
        })
        initRecyclerView()
        swipeRefresh.bind { viewModel.reloadCommand() }
    }

    private fun initRecyclerView() {
        val adapter = PasscodeAdapter(this)
        recyclerView.adapter = adapter
        viewModel.livePagedList.observe(viewLifecycleOwner, Observer<PagedList<Passcode>> {
            if (swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        })
    }
    private fun showActionsDialog() {
        val newFragment = ActionsFragment.newInstance(Passcode())
        newFragment.isCancelable = true
        newFragment.show(requireFragmentManager(), "dialog2")
    }

    override fun onItemClicked(item: Passcode) {
        viewModel.onItemClicked(item)
    }

    override fun onItemLongClicked(item: Passcode) {
       viewModel.onItemLongClicked(item)
    }

    private fun showError(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showInfo(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
