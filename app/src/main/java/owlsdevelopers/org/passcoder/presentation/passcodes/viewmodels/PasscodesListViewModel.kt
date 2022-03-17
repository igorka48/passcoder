package owlsdevelopers.org.passcoder.presentation.passcodes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.domain.core.PagedData
import owlsdevelopers.org.passcoder.domain.models.NetworkState
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.usecases.CopyTextToClipboard
import owlsdevelopers.org.passcoder.domain.usecases.GetPasscodes
import owlsdevelopers.org.passcoder.presentation.core.BasicViewModel
import owlsdevelopers.org.passcoder.presentation.core.ViewEvent
import owlsdevelopers.org.passcoder.presentation.passcodes.navigation.PasscodesNavigationEvents

class PasscodesListViewModel constructor(
    getPasscodes: GetPasscodes,
    private val copyTextToClipboard: CopyTextToClipboard
) : BasicViewModel() {

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
        private const val PAGED_LIST_LOAD_SIZE = 30
        private const val PAGED_LIST_ENABLE_PLACEHOLDERS = true
    }

    private val networkState: MediatorLiveData<NetworkState> = MediatorLiveData()
    private val initialState: MediatorLiveData<NetworkState> = MediatorLiveData()
    private val mLivePagedList: MediatorLiveData<PagedList<Passcode>> = MediatorLiveData()
    val livePagedList: LiveData<PagedList<Passcode>>
      get() = mLivePagedList

    private var pagedData: PagedData<Passcode>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(PAGED_LIST_ENABLE_PLACEHOLDERS)
            .setInitialLoadSizeHint(PAGED_LIST_LOAD_SIZE)
            .setPageSize(PAGED_LIST_PAGE_SIZE)
            .build()

        getPasscodes(
            GetPasscodes.Params(
                username = "",
                pagedListConfig = pagedListConfig
            ), useCaseExceptionHandler
        ) { handleListData(it) }
    }

    private fun handleListData(data: PagedData<Passcode>) {
        pagedData = data
        mLivePagedList.addSource(data.getData()) { mLivePagedList.value = it }
        networkState.addSource(data.getLoadState()) { networkState.value = it }
        initialState.addSource(data.getInitialLoadState()) { initialState.value = it }
    }

    fun reloadCommand() {
        pagedData?.invalidate()
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