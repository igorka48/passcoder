package owlsdevelopers.org.passcoder.domain.repository

import owlsdevelopers.org.passcoder.domain.models.Credential
import owlsdevelopers.org.passcoder.domain.models.User

interface SessionRepository {
   suspend fun createSession(credential: Credential): User
   suspend fun getSession(): User?
   suspend fun deleteSession(): Boolean
}