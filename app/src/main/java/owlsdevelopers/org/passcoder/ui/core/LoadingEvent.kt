package owlsdevelopers.org.passcoder.ui.core


sealed class LoadingEvent {
    object ShowLoading: LoadingEvent()
    object HideLoading: LoadingEvent()
}