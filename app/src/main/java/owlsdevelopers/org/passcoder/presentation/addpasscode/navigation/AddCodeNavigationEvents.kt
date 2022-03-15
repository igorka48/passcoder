package owlsdevelopers.org.passcoder.presentation.addpasscode.navigation

import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class AddCodeNavigationEvents : NavigationEvents {
        object Back : AddCodeNavigationEvents()
}