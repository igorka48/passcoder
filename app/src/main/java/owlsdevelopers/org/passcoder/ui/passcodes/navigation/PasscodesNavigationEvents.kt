package owlsdevelopers.org.passcoder.ui.passcodes.navigation

import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.ui.core.NavigationEvents

sealed class PasscodesNavigationEvents : NavigationEvents {
        data class ShowActions(val passcode: Passcode) : PasscodesNavigationEvents()
}