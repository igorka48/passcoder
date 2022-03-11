package owlsdevelopers.org.passcoder.domain.usecases

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.errors.UserNotFoundError
import owlsdevelopers.org.passcoder.domain.models.User
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository

class GetCurrentUser(private val sessionRepository: SessionRepository) : UseCase<User, UseCase.None>() {
    override suspend fun run(params: None): User {
        return sessionRepository.getSession() ?: throw UserNotFoundError()
    }
}