package owlsdevelopers.org.passcoder.domain.usecases

import owlsdevelopers.org.passcoder.domain.core.UseCase
import owlsdevelopers.org.passcoder.domain.repository.SessionRepository

class IsLogged(private val sessionRepository: SessionRepository): UseCase<Boolean, UseCase.None>(){
    override suspend fun run(params: None): Boolean {
        return sessionRepository.getSession() != null
    }
}