package owlsdevelopers.org.passcoder.domain.usecases

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository

class Login(private val sessionRepository: SessionRepository): UseCase<User, Login.Params>(){

    data class Params(val credential: Credential)

    override suspend fun run(params: Params): User {
        return sessionRepository.createSession(params.credential)
    }
}