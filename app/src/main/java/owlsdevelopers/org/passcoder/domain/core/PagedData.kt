package owlsdevelopers.org.passcoder.domain.core

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.domain.models.NetworkState


interface PagedData<T> {
    fun getData(): LiveData<PagedList<T>>
    fun invalidate()
    fun getInitialLoadState(): LiveData<NetworkState>
    fun getLoadState(): LiveData<NetworkState>
    fun search(keyword: String)
}