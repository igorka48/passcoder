package owlsdevelopers.org.passcoder.ui.login.navigation

import owlsdevelopers.org.passcoder.ui.core.NavigationEvents

sealed class LoginNavigationEvents : NavigationEvents {
        object MainScreen : LoginNavigationEvents()
}