package com.abnormallydriven.paginglibrarytestdrive.names

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
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