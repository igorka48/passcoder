package owlsdevelopers.org.passcoder.domain.core

import androidx.lifecycle.MutableLiveData
import owlsdevelopers.org.passcoder.domain.models.NetworkState

abstract class SearchableDataSourceFactory<T : Any, T2 : Any>: SimpleDataSource<T, T2>() {
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val initialState: MutableLiveData<NetworkState> = MutableLiveData()
    abstract fun setKeyword(keyword: String)
}