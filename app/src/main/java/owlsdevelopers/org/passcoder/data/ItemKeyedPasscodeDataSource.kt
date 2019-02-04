package owlsdevelopers.org.passcoder.data

import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.models.repository.PasscodeRepository

class ItemKeyedPasscodeDataSource(
        private val repository: PasscodeRepository,
        private val userName: String)
    : ItemKeyedDataSource<Long, Passcode>() {

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Passcode>) {
        runBlocking {
            val result = withContext(IO) {
                repository.getPasscodes()
            }
            callback.onResult(result)
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {
        runBlocking {
            val result = withContext(IO) {
                repository.getPasscodes(params.key.toString(), params.requestedLoadSize)
            }
            callback.onResult(result)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {

    }

    override fun getKey(item: Passcode): Long {
        return item.additionTime
    }
}