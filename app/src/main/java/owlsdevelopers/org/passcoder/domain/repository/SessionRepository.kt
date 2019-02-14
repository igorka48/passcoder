package owlsdevelopers.org.passcoder.domain.repository

import owlsdevelopers.org.passcoder.domain.models.User

interface SessionRepository {
   suspend fun createSession(): User
   suspend fun getSession(): User?
   suspend fun deleteSession(): Boolean
}