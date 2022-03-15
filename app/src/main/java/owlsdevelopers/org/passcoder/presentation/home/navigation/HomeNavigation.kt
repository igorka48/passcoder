package owlsdevelopers.org.passcoder.presentation.home.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.presentation.home.fragments.HomeFragment

fun HomeFragment.navigationFun(): (HomeNavigationEvents) -> Unit = {
    when (it) {
        HomeNavigationEvents.AddCode -> {
            navController.navigate(R.id.action_homeFragment_to_addPasscodeFragment)
        }
        HomeNavigationEvents.LoginScreen -> {
            navController.popBackStack(R.id.loginFragment, false)
        }
    }
}