package owlsdevelopers.org.passcoder.presentation.core


sealed class LoadingEvent {
    object ShowLoading: LoadingEvent()
    object HideLoading: LoadingEvent()
}