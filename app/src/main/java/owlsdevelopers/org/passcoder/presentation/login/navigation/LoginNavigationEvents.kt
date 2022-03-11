package owlsdevelopers.org.passcoder.presentation.login.navigation

import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class LoginNavigationEvents : NavigationEvents {
        object MainScreen : LoginNavigationEvents()
}