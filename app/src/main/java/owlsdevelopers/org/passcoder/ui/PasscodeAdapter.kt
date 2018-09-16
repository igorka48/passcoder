package owlsdevelopers.org.passcoder.ui;

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_code.view.*
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.model.Passcode


class PasscodeAdapter(private val callback: Callback) : PagedListAdapter<Passcode, PasscodeAdapter.ViewHolder>(POST_COMPARATOR) {

    companion object {
        const val ITEM_LAYOUT = R.layout.cell_code
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Passcode>() {
            override fun areContentsTheSame(oldItem: Passcode, newItem: Passcode): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Passcode, newItem: Passcode): Boolean =
                    oldItem.value == newItem.value
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(ITEM_LAYOUT, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) as Passcode
        holder.itemView.setOnClickListener { callback.onItemClicked(item) }
        holder.itemView.codeValue.text = item.value
        holder.itemView.codeDescription.text = item.description
    }


    interface Callback {
        fun onItemClicked(item: Passcode)
    }

}
