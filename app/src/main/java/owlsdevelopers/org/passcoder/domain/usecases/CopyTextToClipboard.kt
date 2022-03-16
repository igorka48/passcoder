package owlsdevelopers.org.passcoder.domain.usecases

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.repository.ClipboardRepository

class CopyTextToClipboard(private val repository: ClipboardRepository): UseCase<Boolean, CopyTextToClipboard.Params>(){

    data class Params(val text: String)

    override suspend fun run(params: Params): Boolean {
        repository.copyTextToClipboard(params.text)
        return true
    }
}