package owlsdevelopers.org.passcoder.ui.core

sealed class ViewEvent {
    data class Error(val message: String): ViewEvent()
    data class Info(val message: String): ViewEvent()
    object ShowLoading: ViewEvent()
    object HideLoading: ViewEvent()
}