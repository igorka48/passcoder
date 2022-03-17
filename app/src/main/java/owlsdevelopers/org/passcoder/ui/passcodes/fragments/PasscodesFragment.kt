package owlsdevelopers.org.passcoder.ui.passcodes.fragments

import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.FragmentPasscodesBinding
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.ui.core.BasicFragment
import owlsdevelopers.org.passcoder.ui.core.LoadingEvent
import owlsdevelopers.org.passcoder.ui.core.ViewEvent
import owlsdevelopers.org.passcoder.ui.passcodes.adapters.PasscodeAdapter
import owlsdevelopers.org.passcoder.ui.passcodes.navigation.PasscodesNavigationEvents
import owlsdevelopers.org.passcoder.ui.passcodes.navigation.navigationFun
import owlsdevelopers.org.passcoder.ui.passcodes.viewmodels.PasscodesListViewModel

class PasscodesFragment : BasicFragment<PasscodesNavigationEvents>(R.layout.fragment_passcodes, childNavigation = true),
    PasscodeAdapter.Callback {
    private val viewModel by viewModel<PasscodesListViewModel>()
    override fun provideViewModel() = viewModel
    override fun provideNavigationFunction() = navigationFun()
    lateinit var viewBinding: FragmentPasscodesBinding
    override fun initViews() {
        viewBinding = FragmentPasscodesBinding.bind(requireView())
        with(viewBinding){
            swipeRefresh.bind { viewModel.reloadCommand() }
            viewModel.loadingEvent.observe(viewLifecycleOwner) {
                when(it){
                    is LoadingEvent.ShowLoading -> swipeRefresh.isRefreshing = true
                    is LoadingEvent.HideLoading -> swipeRefresh.isRefreshing = false
                }
            }
        }
        viewModel.viewEvent.observe(viewLifecycleOwner) {
            when(it){
                is ViewEvent.Error -> showError(it.exception.localizedMessage ?: "")
                is ViewEvent.Info -> showInfo(it.message)
            }
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = PasscodeAdapter(this)
        viewBinding.recyclerView.adapter = adapter
        viewModel.livePagedList.observe(viewLifecycleOwner) {
            if (viewBinding.swipeRefresh.isRefreshing)
                viewBinding.swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        }
    }

    override fun onItemClicked(item: Passcode) {
        viewModel.itemClickedCommand(item)
    }

    override fun onItemLongClicked(item: Passcode) {
        viewModel.showActionsCommand(item)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showInfo(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
