package owlsdevelopers.org.passcoder.presentation.addpasscode.navigation

import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.presentation.addpasscode.fragments.AddPasscodeFragment

fun AddPasscodeFragment.navigationFun(): (AddCodeNavigationEvents) -> Unit = {
    when (it) {
        AddCodeNavigationEvents.Back -> {
            navController.popBackStack()
        }
    }
}