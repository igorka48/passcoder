package owlsdevelopers.org.passcoder.domain.repository

interface ClipboardRepository {
   fun copyTextToClipboard(text: String)
}