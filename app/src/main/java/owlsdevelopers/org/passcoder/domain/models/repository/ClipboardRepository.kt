package owlsdevelopers.org.passcoder.domain.models.repository

interface ClipboardRepository {
   fun copyTextToClipboard(text: String)
}