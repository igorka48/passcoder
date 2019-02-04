package owlsdevelopers.org.passcoder.domain.core

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.domain.models.NetworkState


class AndroidArchPagedData<T>(
        private val pagedList: LiveData<PagedList<T>>,
        private val dataSourceFactory: SearchableDataSourceFactory<*, T>
) : PagedData<T> {

    companion object {
        suspend fun <T> BuildPagedData(
                sourceFactory: SearchableDataSourceFactory<*, T>,
                config: PagedList.Config
        ): PagedData<T> {

            val livePagedList = LivePagedListBuilder(sourceFactory, config)
                .build()
            return AndroidArchPagedData(livePagedList, sourceFactory)
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

    override fun getData(): LiveData<PagedList<T>> {
        return pagedList
    }

    override fun invalidate() {
        pagedList.value?.dataSource?.invalidate()
    }
}