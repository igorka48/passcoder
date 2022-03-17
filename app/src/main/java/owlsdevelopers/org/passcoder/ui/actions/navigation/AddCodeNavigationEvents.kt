package owlsdevelopers.org.passcoder.ui.actions.navigation

import owlsdevelopers.org.passcoder.ui.core.NavigationEvents

sealed class ActionsNavigationEvents : NavigationEvents {
        object Back : ActionsNavigationEvents()
}