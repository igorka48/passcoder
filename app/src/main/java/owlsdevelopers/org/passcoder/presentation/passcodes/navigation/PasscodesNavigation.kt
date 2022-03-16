package owlsdevelopers.org.passcoder.presentation.passcodes.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.presentation.passcodes.fragments.PasscodesFragment

fun PasscodesFragment.navigationFun(): (PasscodesNavigationEvents) -> Unit = {
    when (it) {
        PasscodesNavigationEvents.ShowActions -> {
            navController.navigate(R.id.action_homeFragment_to_actionsFragment)
        }
    }
}