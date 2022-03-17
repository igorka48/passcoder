package owlsdevelopers.org.passcoder.ui.core


sealed class ViewEvent {
    data class Error(val exception: Exception): ViewEvent()
    data class Info(val message: String): ViewEvent()
}