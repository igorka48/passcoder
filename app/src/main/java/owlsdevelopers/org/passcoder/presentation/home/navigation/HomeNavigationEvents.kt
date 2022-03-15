package owlsdevelopers.org.passcoder.presentation.home.navigation

import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class HomeNavigationEvents : NavigationEvents {
        object LoginScreen : HomeNavigationEvents()
        object AddCode : HomeNavigationEvents()
}