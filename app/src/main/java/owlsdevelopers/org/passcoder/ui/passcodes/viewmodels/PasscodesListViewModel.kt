package owlsdevelopers.org.passcoder.ui.passcodes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.data.PasscodeDataSourceFactory
import owlsdevelopers.org.passcoder.model.Passcode
import owlsdevelopers.org.passcoder.model.repository.ClipboardRepository
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent


class PasscodesListViewModel constructor(passcodeRepository: PasscodeRepository, private val clipboardRepository: ClipboardRepository) : ViewModel() {

    val livePagedList: LiveData<PagedList<Passcode>>
    private val mToastInfo = SingleLiveEvent<String>()
    private val mLoadIndicator = SingleLiveEvent<Boolean>()
    private val mShowActions = SingleLiveEvent<Boolean>()

    val toastInfo: LiveData<String>
        get() = mToastInfo

    val loadIndicator: LiveData<Boolean>
        get() = mLoadIndicator

    val showActions: LiveData<Boolean>
        get() = mShowActions

    init {

        val dataSourceFactory = PasscodeDataSourceFactory(passcodeRepository, "")

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPageSize(20)
                .build()


        livePagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
                .build()
    }


    fun onItemClicked(item: Passcode) {
        clipboardRepository.copyTextToClipboard(item.value)
        mToastInfo.value = "Code copied to clipboard"
    }

    fun reloadData() {
        livePagedList.value?.dataSource?.invalidate()
    }

    fun onItemLongClicked(item: Passcode) {
        mShowActions.value = true
    }
}