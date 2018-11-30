package owlsdevelopers.org.passcoder.data

import androidx.paging.ItemKeyedDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import owlsdevelopers.org.passcoder.model.Passcode

class ItemKeyedPasscodeDataSource(
        private val databaseReference: DatabaseReference,
        private val userName: String)
    : ItemKeyedDataSource<Long, Passcode>() {
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Passcode>) {
        databaseReference.addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val result = mutableListOf<Passcode>()
                        for (ds in p0.children) {
                            val code = ds.getValue(Passcode::class.java)
                            code?.let {
                                result.add(it)
                            }
                        }
                        callback.onResult(result)
                    }
                }
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Passcode>) {
        databaseReference.startAt(params.key.toString()).limitToFirst(params.requestedLoadSize).addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {


                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val result = mutableListOf<Passcode>()
                        for (ds in p0.children) {
                            val code = ds.getValue(Passcode::class.java)
                            code?.let {
                                result.add(it)
                            }
                        }
                    }
                }
        )

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