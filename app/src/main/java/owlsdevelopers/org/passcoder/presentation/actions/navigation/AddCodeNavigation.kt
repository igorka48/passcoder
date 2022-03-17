package owlsdevelopers.org.passcoder.presentation.actions.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.presentation.actions.fragments.ActionsFragment
import owlsdevelopers.org.passcoder.presentation.addpasscode.fragments.AddPasscodeFragment

fun ActionsFragment.navigationFun(): (ActionsNavigationEvents) -> Unit = {
    when (it) {
        ActionsNavigationEvents.Back -> {
            navController.popBackStack()
        }
    }
}