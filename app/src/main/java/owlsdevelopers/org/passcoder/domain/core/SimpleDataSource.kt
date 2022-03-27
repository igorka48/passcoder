package owlsdevelopers.org.passcoder.domain.core


import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import owlsdevelopers.org.passcoder.domain.models.NetworkState


abstract class SimpleDataSource<T : Any, T2 : Any>: PagingSource<T, T2>(){
    abstract fun getInitialLoadState(): LiveData<NetworkState>
    abstract fun getLoadState(): LiveData<NetworkState>
}