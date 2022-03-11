package owlsdevelopers.org.passcoder.presentation.passcodes.navigation

import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class HomeNavigationEvents : NavigationEvents {
        object MainScreen : HomeNavigationEvents()
        object LoginScreen : HomeNavigationEvents()
        object AddCode : HomeNavigationEvents()
}