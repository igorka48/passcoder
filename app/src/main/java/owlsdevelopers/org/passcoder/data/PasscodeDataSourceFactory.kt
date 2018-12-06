package owlsdevelopers.org.passcoder.data

import androidx.paging.DataSource
import owlsdevelopers.org.passcoder.data.repository.FirebasePasscodeRepository
import owlsdevelopers.org.passcoder.model.Passcode

class PasscodeDataSourceFactory(
        private val passcodeRepository: FirebasePasscodeRepository,
        private val userName: String) : DataSource.Factory<Long, Passcode>() {
    override fun create(): DataSource<Long, Passcode> {
        val source  = ItemKeyedPasscodeDataSource(passcodeRepository, userName)
        return source
    }
}
