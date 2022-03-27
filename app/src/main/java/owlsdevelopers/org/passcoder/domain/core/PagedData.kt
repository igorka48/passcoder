package owlsdevelopers.org.passcoder.domain.core

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import owlsdevelopers.org.passcoder.domain.models.NetworkState


interface PagedData<T : Any> {
    fun getData(): Flow<PagingData<T>>
    fun invalidate()
    fun getInitialLoadState(): LiveData<NetworkState>
    fun getLoadState(): LiveData<NetworkState>
    fun search(keyword: String)
}