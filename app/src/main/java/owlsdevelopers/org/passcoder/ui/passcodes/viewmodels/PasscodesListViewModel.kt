package owlsdevelopers.org.passcoder.ui.passcodes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.data.PasscodeDataSourceFactory
import owlsdevelopers.org.passcoder.model.Passcode
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.ui.util.SingleLiveEvent


class PasscodesListViewModel constructor(passcodeRepository: PasscodeRepository) : ViewModel() {

    val livePagedList: LiveData<PagedList<Passcode>>
    private val mToastInfo = SingleLiveEvent<String>()
    private val mLoadIndicator = SingleLiveEvent<Boolean>()

    val toastInfo: LiveData<String>
        get() = mToastInfo

    val loadIndicator: LiveData<Boolean>
        get() = mLoadIndicator


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
//        val clipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        val clip = ClipData.newPlainText(item.value, item.value)
//        clipboard.primaryClip = clip
//        Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_LONG).show()
        mToastInfo.value = "Code copied to clipboard"
    }

    fun reloadData() {
        livePagedList.value?.dataSource?.invalidate()
    }
}