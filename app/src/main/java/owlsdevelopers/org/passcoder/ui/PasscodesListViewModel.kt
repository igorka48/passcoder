package com.abnormallydriven.paginglibrarytestdrive.names

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import owlsdevelopers.org.passcoder.data.PasscodeDataSourceFactory
import owlsdevelopers.org.passcoder.model.Passcode


class PasscodesListViewModel constructor(private val dataSourceFactory: PasscodeDataSourceFactory) : ViewModel() {

    val livePagedListOfNames: LiveData<PagedList<Passcode>>

    init {

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPageSize(20)
                .build()



        livePagedListOfNames = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
                .build()
    }

}