package owlsdevelopers.org.passcoder.presentation.passcodes.fragments

import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.FragmentPasscodesBinding
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.presentation.core.BasicFragment
import owlsdevelopers.org.passcoder.presentation.core.LoadingEvent
import owlsdevelopers.org.passcoder.presentation.core.ViewEvent
import owlsdevelopers.org.passcoder.presentation.passcodes.adapters.PasscodeAdapter
import owlsdevelopers.org.passcoder.presentation.passcodes.navigation.PasscodesNavigationEvents
import owlsdevelopers.org.passcoder.presentation.passcodes.navigation.navigationFun
import owlsdevelopers.org.passcoder.presentation.passcodes.viewmodels.PasscodesListViewModel
import owlsdevelopers.org.passcoder.presentation.util.bind


class PasscodesFragment : BasicFragment<PasscodesNavigationEvents>(R.layout.fragment_passcodes, childNavigation = true),
    PasscodeAdapter.Callback {
    private val viewModel by viewModel<PasscodesListViewModel>()
    override fun provideViewModel() = viewModel
    override fun provideNavigationFunction() = navigationFun()
    lateinit var viewBinding: FragmentPasscodesBinding
    override fun initViews() {
        viewBinding = FragmentPasscodesBinding.bind(requireView())
        viewModel.showActions.observe(viewLifecycleOwner) { value -> value?.let { viewModel::showActionsCommand } }

        viewModel.viewEvent.observe(viewLifecycleOwner) {
            when(it){
                is ViewEvent.Error -> showError(it.exception.localizedMessage ?: "")
                is ViewEvent.Info -> showInfo(it.message)
            }
        }
        viewModel.loadingEvent.observe(viewLifecycleOwner) {
            when(it){
                is LoadingEvent.ShowLoading -> viewBinding.swipeRefresh.isRefreshing = true
                is LoadingEvent.HideLoading -> viewBinding.swipeRefresh.isRefreshing = false
            }
        }
        initRecyclerView()
        viewBinding.swipeRefresh.bind { viewModel.reloadCommand() }
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
        viewModel.onItemClicked(item)
    }

    override fun onItemLongClicked(item: Passcode) {
        viewModel.onItemLongClicked(item)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showInfo(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
