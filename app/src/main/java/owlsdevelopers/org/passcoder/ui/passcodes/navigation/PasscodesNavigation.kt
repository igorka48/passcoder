package owlsdevelopers.org.passcoder.ui.passcodes.navigation

import owlsdevelopers.org.passcoder.ui.home.fragments.HomeFragmentDirections
import owlsdevelopers.org.passcoder.ui.passcodes.fragments.PasscodesFragment

fun PasscodesFragment.navigationFun(): (PasscodesNavigationEvents) -> Unit = {
    when (it) {
        is PasscodesNavigationEvents.ShowActions -> {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToActionsFragment(it.passcode))
        }
    }
}