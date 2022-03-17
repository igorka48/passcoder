package owlsdevelopers.org.passcoder.ui.actions.navigation

import owlsdevelopers.org.passcoder.ui.actions.fragments.ActionsFragment

fun ActionsFragment.navigationFun(): (ActionsNavigationEvents) -> Unit = {
    when (it) {
        ActionsNavigationEvents.Back -> {
            navController.popBackStack()
        }
    }
}