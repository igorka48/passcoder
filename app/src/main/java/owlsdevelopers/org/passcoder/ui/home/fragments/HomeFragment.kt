package owlsdevelopers.org.passcoder.ui.home.fragments

import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.FragmentHomeBinding
import owlsdevelopers.org.passcoder.ui.core.BasicFragment
import owlsdevelopers.org.passcoder.ui.home.navigation.HomeNavigationEvents
import owlsdevelopers.org.passcoder.ui.home.navigation.navigationFun
import owlsdevelopers.org.passcoder.ui.home.viewmodels.HomeViewModel


class HomeFragment : BasicFragment<HomeNavigationEvents>(R.layout.fragment_home) {
    private val viewModel by viewModel<HomeViewModel>()
    override fun provideViewModel() = viewModel
    override fun provideNavigationFunction() = navigationFun()

    override fun initViews() {
        val viewBinding = FragmentHomeBinding.bind(requireView())
        setHasOptionsMenu(true)
        viewBinding.addCodeFab.setOnClickListener {
            viewModel.addCodeCommand()
        }
        viewBinding.toolbar.inflateMenu(R.menu.home)
        viewBinding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_logout -> {
                    viewModel.logoutCommand()
                    true
                }
                else -> false
            }
        }
    }
}
