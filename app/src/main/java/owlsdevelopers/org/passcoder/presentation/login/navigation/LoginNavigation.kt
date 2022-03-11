package owlsdevelopers.org.passcoder.presentation.login.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.presentation.login.fragments.LoginFragment

fun LoginFragment.navigationFun(): (LoginNavigationEvents) -> Unit = {
    when (it) {
        is LoginNavigationEvents.MainScreen -> {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
}