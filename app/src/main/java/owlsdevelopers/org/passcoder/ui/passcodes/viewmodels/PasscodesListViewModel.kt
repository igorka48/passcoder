package owlsdevelopers.org.passcoder.ui.passcodes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.domain.core.PagedData
import owlsdevelopers.org.passcoder.domain.models.NetworkState
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.models.repository.ClipboardRepository
import owlsdevelopers.org.passcoder.domain.usecases.GetPasscodes
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent


class PasscodesListViewModel constructor(getPasscodes: GetPasscodes, private val clipboardRepository: ClipboardRepository) : ViewModel() {

    private val mToastInfo = SingleLiveEvent<String>()
    private val mLoadIndicator = SingleLiveEvent<Boolean>()
    private val mShowActions = SingleLiveEvent<Boolean>()

    val toastInfo: LiveData<String>
        get() = mToastInfo

    val loadIndicator: LiveData<Boolean>
        get() = mLoadIndicator

    val showActions: LiveData<Boolean>
        get() = mShowActions


    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
        private const val PAGED_LIST_LOAD_SIZE = 30
        private const val PAGED_LIST_ENABLE_PLACEHOLDERS = true
    }

    val networkState: MediatorLiveData<NetworkState> = MediatorLiveData()
    val initialState: MediatorLiveData<NetworkState> = MediatorLiveData()
    val livePagedList: MediatorLiveData<PagedList<Passcode>> = MediatorLiveData()

    private var pagedData: PagedData<Passcode>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(PAGED_LIST_ENABLE_PLACEHOLDERS)
                .setInitialLoadSizeHint(PAGED_LIST_LOAD_SIZE)
                .setPageSize(PAGED_LIST_PAGE_SIZE)
                .build()

        getPasscodes(GetPasscodes.Params(username = "", pagedListConfig = pagedListConfig)) { handleListData(it) }
    }

    private fun handleListData(data: PagedData<Passcode>) {
        pagedData = data
        livePagedList.addSource(data.getData()) { livePagedList.value = it }
        networkState.addSource(data.getLoadState()) { networkState.value = it }
        initialState.addSource(data.getInitialLoadState()) { initialState.value = it }
    }

    fun reloadData() {
        pagedData?.invalidate()
    }



    fun onItemClicked(item: Passcode) {
        clipboardRepository.copyTextToClipboard(item.value)
        mToastInfo.value = "Code copied to clipboard"
    }


    fun onItemLongClicked(item: Passcode) {
        mShowActions.value = true
    }
}