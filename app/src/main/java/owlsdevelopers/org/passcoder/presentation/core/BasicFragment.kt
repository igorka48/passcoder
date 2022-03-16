package owlsdevelopers.org.passcoder.presentation.core

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import owlsdevelopers.org.passcoder.R


abstract class BasicFragment<T: NavigationEvents>(@LayoutRes contentLayoutId: Int, val childNavigation: Boolean = false): Fragment(contentLayoutId) {

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = if(childNavigation){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        } else {
            Navigation.findNavController(view)
        }
        navController.addOnDestinationChangedListener { _, _, _ -> hideKeyboard() }
//        toolbar?.let {
//            NavigationUI.setupWithNavController(it, navController)
//        }
        val navigationFunction = provideNavigationFunction()
        provideViewModel().navigationEvents.observe(viewLifecycleOwner, Observer {
            navigationFunction(it as T)
        })
        initViews()
    }


    fun hideKeyboard(){
        val inputMethodManager = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    abstract fun provideNavigationFunction(): (T) -> Unit
    abstract fun provideViewModel(): BasicViewModel
    abstract fun initViews()
}