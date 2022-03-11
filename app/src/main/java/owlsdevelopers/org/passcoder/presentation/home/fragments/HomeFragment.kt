package owlsdevelopers.org.passcoder.presentation.home.fragments

import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.FragmentHomeBinding
import owlsdevelopers.org.passcoder.presentation.core.BasicFragment
import owlsdevelopers.org.passcoder.presentation.passcodes.navigation.HomeNavigationEvents
import owlsdevelopers.org.passcoder.presentation.passcodes.navigation.navigationFun
import owlsdevelopers.org.passcoder.presentation.passcodes.viewmodels.HomeViewModel


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
        viewBinding.toolbar.inflateMenu(R.menu.main)
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





//    private fun showAddCodeDialog() {
//        val newFragment = AddPasscodeFragment.newInstance()
//        newFragment.isCancelable = false
//        newFragment.show(supportFragmentManager, "dialog")
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main, menu)
//    }

//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_logout -> {
//                viewModel.logoutCommand()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun logout() {
//        FirebaseAuth.getInstance().signOut()
//        // Configure Google Sign In
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()
//        GoogleSignIn.getClient(this, gso).signOut()
//        startActivity(MainActivity.getIntent(this))
//        finish()
    }


}
