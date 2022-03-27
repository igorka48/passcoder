package owlsdevelopers.org.passcoder.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingState
import owlsdevelopers.org.passcoder.domain.core.SearchableDataSourceFactory
import owlsdevelopers.org.passcoder.domain.models.NetworkState
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.repository.PasscodeRepository

class PasscodeDataSourceFactory(
        private val passcodeRepository: PasscodeRepository,
        private val userName: String) : SearchableDataSourceFactory<String, Passcode>() {

    override fun setKeyword(keyword: String) {}

    override fun getInitialLoadState(): LiveData<NetworkState> = initialState

    override fun getLoadState(): LiveData<NetworkState> = networkState

    override fun getRefreshKey(state: PagingState<String, Passcode>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.value
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Passcode> {
        val result = if(params.key.isNullOrBlank()) {
            passcodeRepository.getPasscodes()
        } else {
            passcodeRepository.getPasscodes(params.key!!, params.loadSize)
        }
        return LoadResult.Page(
            data = result,
            prevKey = null, // Only paging forward.
            nextKey = result.lastOrNull()?.value
        )
    }
}
