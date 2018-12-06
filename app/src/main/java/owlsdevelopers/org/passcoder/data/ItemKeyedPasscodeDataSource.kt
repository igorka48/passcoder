package owlsdevelopers.org.passcoder.data

import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.*
import owlsdevelopers.org.passcoder.model.Passcode
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository

class ItemKeyedPasscodeDataSource(
        private val repository: PasscodeRepository,
        private val userName: String)
    : ItemKeyedDataSource<Long, Passcode>() {

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Passcode>) {
        runBlocking {
            val result = GlobalScope.async {
                repository.getPasscodes()
            }.await()
            callback.onResult(result)
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {
        runBlocking {
            val result = GlobalScope.async {
                repository.getPasscodes(params.key.toString(), params.requestedLoadSize)
            }.await()
            callback.onResult(result)
        }

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {

    }

    override fun getKey(item: Passcode): Long {
        return item.additionTime
    }
}