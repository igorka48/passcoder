package owlsdevelopers.org.passcoder.ui.login.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.ui.login.fragments.LoginFragment

fun LoginFragment.navigationFun(): (LoginNavigationEvents) -> Unit = {
    when (it) {
        is LoginNavigationEvents.MainScreen -> {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
}