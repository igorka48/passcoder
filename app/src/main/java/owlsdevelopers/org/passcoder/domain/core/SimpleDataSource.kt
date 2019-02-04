package owlsdevelopers.org.passcoder.domain.core


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import owlsdevelopers.org.passcoder.domain.models.NetworkState


abstract class SimpleDataSource<T, T2>: DataSource.Factory<T, T2>(){
    abstract fun getInitialLoadState(): LiveData<NetworkState>
    abstract fun getLoadState(): LiveData<NetworkState>
}