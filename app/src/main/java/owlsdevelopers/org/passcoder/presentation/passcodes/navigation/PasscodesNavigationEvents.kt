package owlsdevelopers.org.passcoder.presentation.passcodes.navigation

import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.presentation.core.NavigationEvents

sealed class PasscodesNavigationEvents : NavigationEvents {
        data class ShowActions(val passcode: Passcode) : PasscodesNavigationEvents()
}