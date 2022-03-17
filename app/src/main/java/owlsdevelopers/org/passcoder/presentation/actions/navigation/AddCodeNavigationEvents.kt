package owlsdevelopers.org.passcoder.presentation.actions.navigation

import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class ActionsNavigationEvents : NavigationEvents {
        object Back : ActionsNavigationEvents()
}