package owlsdevelopers.org.passcoder.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import owlsdevelopers.org.passcoder.domain.core.SearchableDataSourceFactory
import owlsdevelopers.org.passcoder.domain.models.NetworkState
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository

class PasscodeDataSourceFactory(
        private val passcodeRepository: PasscodeRepository,
        private val userName: String) : SearchableDataSourceFactory<Long, Passcode>() {

    override fun setKeyword(keyword: String) {}

    override fun getInitialLoadState(): LiveData<NetworkState> = initialState

    override fun getLoadState(): LiveData<NetworkState> = networkState

    override fun create(): DataSource<Long, Passcode> {
        val source = ItemKeyedPasscodeDataSource(passcodeRepository, userName)
        return source
    }
}
