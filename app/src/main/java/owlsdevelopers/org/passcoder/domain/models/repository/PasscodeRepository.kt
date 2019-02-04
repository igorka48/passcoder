package owlsdevelopers.org.passcoder.domain.models.repository

import owlsdevelopers.org.passcoder.domain.models.Passcode

interface PasscodeRepository {
    suspend fun getPasscodes(): List<Passcode>
    suspend fun getPasscodes(fromKey: String, limit: Int): List<Passcode>
    suspend fun addPasscode(passcode: Passcode): Boolean
}