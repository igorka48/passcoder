package owlsdevelopers.org.passcoder.domain.usecases

import kotlinx.coroutines.delay
import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.models.repository.PasscodeRepository


class AddPasscode(val passcodeRepository: PasscodeRepository) : UseCase<Boolean, AddPasscode.Params>() {

    data class Params(val passcode: Passcode)

    override suspend fun run(params: Params): Boolean {
        delay(3000)
        return passcodeRepository.addPasscode(params.passcode)
    }
}