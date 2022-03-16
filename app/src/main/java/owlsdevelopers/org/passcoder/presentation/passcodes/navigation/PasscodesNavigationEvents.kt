package owlsdevelopers.org.passcoder.presentation.passcodes.navigation

import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class PasscodesNavigationEvents : NavigationEvents {
        object ShowActions : PasscodesNavigationEvents()
}