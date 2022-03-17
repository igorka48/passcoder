package owlsdevelopers.org.passcoder.ui.home.navigation

import owlsdevelopers.org.passcoder.ui.core.NavigationEvents

sealed class HomeNavigationEvents : NavigationEvents {
        object LoginScreen : HomeNavigationEvents()
        object AddCode : HomeNavigationEvents()
}