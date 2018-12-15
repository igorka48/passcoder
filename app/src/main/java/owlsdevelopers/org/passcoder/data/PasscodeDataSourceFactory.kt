package owlsdevelopers.org.passcoder.data

import androidx.paging.DataSource
import owlsdevelopers.org.passcoder.model.Passcode
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository

class PasscodeDataSourceFactory(
        private val passcodeRepository: PasscodeRepository,
        private val userName: String) : DataSource.Factory<Long, Passcode>() {
    override fun create(): DataSource<Long, Passcode> {
        val source  = ItemKeyedPasscodeDataSource(passcodeRepository, userName)
        return source
    }
}
