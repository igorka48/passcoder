package owlsdevelopers.org.passcoder.ui.addpasscode.navigation

import owlsdevelopers.org.passcoder.ui.addpasscode.fragments.AddPasscodeFragment

fun AddPasscodeFragment.navigationFun(): (AddCodeNavigationEvents) -> Unit = {
    when (it) {
        AddCodeNavigationEvents.Back -> {
            navController.popBackStack()
        }
    }
}