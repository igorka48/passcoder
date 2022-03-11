package owlsdevelopers.org.passcoder.presentation.passcodes.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.presentation.home.fragments.HomeFragment

fun HomeFragment.navigationFun(): (HomeNavigationEvents) -> Unit = {
    when (it) {
        is HomeNavigationEvents.MainScreen -> {
            //navController.navigate(R.id.action_loginFragment_to_passcodesFragment)
        }
        HomeNavigationEvents.AddCode -> {

        }
        HomeNavigationEvents.LoginScreen -> {
            navController.popBackStack(R.id.loginFragment, false)
        }
    }
}