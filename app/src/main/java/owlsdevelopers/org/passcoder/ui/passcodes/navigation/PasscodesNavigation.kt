package owlsdevelopers.org.passcoder.ui.passcodes.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.ui.passcodes.fragments.PasscodesFragment

fun PasscodesFragment.navigationFun(): (PasscodesNavigationEvents) -> Unit = {
    when (it) {
        PasscodesNavigationEvents.ShowActions -> {
            navController.navigate(R.id.action_homeFragment_to_actionsFragment)
        }
    }
}