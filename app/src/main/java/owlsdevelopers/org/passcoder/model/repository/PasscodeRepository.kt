package owlsdevelopers.org.passcoder.model.repository

import owlsdevelopers.org.passcoder.model.Passcode

interface PasscodeRepository {
    suspend fun getPasscodes(): List<Passcode>
    suspend fun getPasscodes(fromKey: String, limit: Int): List<Passcode>
}