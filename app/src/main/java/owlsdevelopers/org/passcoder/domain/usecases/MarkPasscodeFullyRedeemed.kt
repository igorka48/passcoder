package owlsdevelopers.org.passcoder.domain.usecases

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository


class MarkPasscodeFullyRedeemed(val passcodeRepository: PasscodeRepository) : UseCase<Boolean, MarkPasscodeFullyRedeemed.Params>() {

    data class Params(val passcode: Passcode)

    override suspend fun run(params: Params): Boolean {
        return passcodeRepository.fullyRedeemPasscode(params.passcode)
    }
}