package owlsdevelopers.org.passcoder.domain.core

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import owlsdevelopers.org.passcoder.domain.models.NetworkState


class AndroidArchPagedData<T : Any>(
    private val pagedList: Pager<out Any, T>,
    private val dataSourceFactory: SearchableDataSourceFactory<*, T>
) : PagedData<T> {

    companion object {
        fun <T : Any> buildPagedData(
                sourceFactory: SearchableDataSourceFactory<*, T>,
                config: PagingConfig
        ): PagedData<T> {

           val pager = Pager(
                // Configure how data is loaded by passing additional properties to
                // PagingConfig, such as prefetchDistance.
               config
            ) {sourceFactory}
           return AndroidArchPagedData(pager, sourceFactory)
        }
    }


    override fun search(keyword: String) {
        dataSourceFactory.setKeyword(keyword)
        invalidate()
    }

    override fun getInitialLoadState(): LiveData<NetworkState> {
        return dataSourceFactory.getInitialLoadState()
    }

    override fun getLoadState(): LiveData<NetworkState> {
        return dataSourceFactory.getLoadState()
    }

    override fun getData(): Flow<PagingData<T>> {
        return pagedList.flow
    }

    override fun invalidate() {
    }
}