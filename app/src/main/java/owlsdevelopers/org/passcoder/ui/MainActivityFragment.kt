package owlsdevelopers.org.passcoder.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.abnormallydriven.paginglibrarytestdrive.names.PasscodesListViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_main.*
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.data.PasscodeDataSourceFactory
import owlsdevelopers.org.passcoder.model.Passcode


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), PasscodeAdapter.Callback {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("passcodes")

    private var viewModel: PasscodesListViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        swipeRefresh.setOnRefreshListener { viewModel?.livePagedListOfNames?.value?.dataSource?.invalidate() }
    }

    private fun initRecyclerView() {
        val adapter = PasscodeAdapter(this)
        recyclerView.adapter = adapter
        viewModel = PasscodesListViewModel(PasscodeDataSourceFactory(mDatabase, ""))
        viewModel?.livePagedListOfNames?.observe(this, Observer<PagedList<Passcode>> {
            if (swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        })
    }


    override fun onItemClicked(item: Passcode) {
        val clipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(item.value, item.value)
        clipboard.primaryClip = clip
        Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_LONG).show()
    }

}
