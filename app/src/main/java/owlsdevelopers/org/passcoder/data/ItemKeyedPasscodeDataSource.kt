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


//    override fun getKey(item: Passcode) = item.id ?: 0
//
//
//    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {
//    }
//
//    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {
//        val request = databaseReference.getPostsBefore(userName, true, params.key, params.requestedLoadSize)
//        try {
//            val response = request.execute()
//            val items = response.body() ?: emptyList()
//            if (!items.isEmpty())
//                Log.d("API:", "received: " + items.size + " items. Last id: " + items[items.size - 1].id)
//            callback.onResult(items)
//        } catch (ioException: IOException) {
//
//        }
//    }
//
//
//    override fun loadInitial(
//            params: LoadInitialParams<Long>,
//            callback: LoadInitialCallback<Passcode>) {
//        val request = twitterApi.getPosts(userName, true, params.requestedLoadSize)
//        try {
//            val response = request.execute()
//            val items = response.body() ?: emptyList()
//            if (!items.isEmpty())
//                Log.d("API:", "received: " + items.size + " items. Last id: " + items[items.size - 1].id)
//            callback.onResult(items)
//        } catch (ioException: IOException) {
//
//        }
//    }
}