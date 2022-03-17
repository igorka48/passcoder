package owlsdevelopers.org.passcoder.ui.addpasscode.navigation

import owlsdevelopers.org.passcoder.ui.core.NavigationEvents

sealed class AddCodeNavigationEvents : NavigationEvents {
        object Back : AddCodeNavigationEvents()
}