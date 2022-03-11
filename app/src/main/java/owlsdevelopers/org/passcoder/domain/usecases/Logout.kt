package owlsdevelopers.org.passcoder.domain.usecases

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository

class Logout(private val sessionRepository: SessionRepository): UseCase<Boolean, UseCase.None>(){

    data class Params(val credential: Credential)

    override suspend fun run(params: None): Boolean {
        return sessionRepository.deleteSession()
    }
}