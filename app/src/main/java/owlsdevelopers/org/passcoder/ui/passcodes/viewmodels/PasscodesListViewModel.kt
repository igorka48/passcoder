package owlsdevelopers.org.passcoder.ui.passcodes.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import owlsdevelopers.org.passcoder.domain.core.PagedData
import owlsdevelopers.org.passcoder.domain.models.NetworkState
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.usecases.CopyTextToClipboard
import owlsdevelopers.org.passcoder.domain.usecases.GetPasscodes
import owlsdevelopers.org.passcoder.ui.core.BasicViewModel
import owlsdevelopers.org.passcoder.ui.core.ViewEvent
import owlsdevelopers.org.passcoder.ui.passcodes.navigation.PasscodesNavigationEvents

class PasscodesListViewModel constructor(
    getPasscodes: GetPasscodes,
    private val copyTextToClipboard: CopyTextToClipboard,
) : BasicViewModel() {

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
        private const val PAGED_LIST_LOAD_SIZE = 30
        private const val PAGED_LIST_ENABLE_PLACEHOLDERS = true
    }

    private val networkState: MediatorLiveData<NetworkState> = MediatorLiveData()
    private val initialState: MediatorLiveData<NetworkState> = MediatorLiveData()
    lateinit var livePagedList: Flow<PagingData<Passcode>>


    init {
        val pagedListConfig = PagingConfig(
            pageSize = PAGED_LIST_PAGE_SIZE,
            enablePlaceholders = PAGED_LIST_ENABLE_PLACEHOLDERS,
            initialLoadSize = PAGED_LIST_LOAD_SIZE
        )

        viewModelScope.launch {
            withContext(Dispatchers.Main.immediate) {
                handleListData(
                    getPasscodes.run(
                        GetPasscodes.Params(
                            username = "",
                            pagedListConfig = pagedListConfig
                        )
                    )
                )
            }
        }
    }

    private fun handleListData(data: PagedData<Passcode>) {
        livePagedList = data.getData().cachedIn(viewModelScope)
        networkState.addSource(data.getLoadState()) { networkState.value = it }
        initialState.addSource(data.getInitialLoadState()) { initialState.value = it }
    }

    fun reloadCommand() {

    }

    fun showActionsCommand(item: Passcode) {
        mNavigationEvents.postValue(PasscodesNavigationEvents.ShowActions(item))
    }

    fun itemClickedCommand(item: Passcode) {
        copyTextToClipboard(CopyTextToClipboard.Params(item.value), useCaseExceptionHandler) {
            mViewEvent.postValue(ViewEvent.Info("Code copied to clipboard"))
        }
    }

}