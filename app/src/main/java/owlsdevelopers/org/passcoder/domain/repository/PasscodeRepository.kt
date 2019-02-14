package owlsdevelopers.org.passcoder.domain.repository

import owlsdevelopers.org.passcoder.domain.models.Passcode

interface PasscodeRepository {
    suspend fun getPasscodes(): List<Passcode>
    suspend fun getPasscodes(fromKey: String, limit: Int): List<Passcode>
    suspend fun addPasscode(passcode: Passcode): Boolean
    suspend fun redeemPasscode(passcode: Passcode): Boolean
    suspend fun fullyRedeemPasscode(passcode: Passcode): Boolean
}