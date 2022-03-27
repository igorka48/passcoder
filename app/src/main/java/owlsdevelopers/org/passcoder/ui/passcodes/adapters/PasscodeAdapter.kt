package owlsdevelopers.org.passcoder.ui.passcodes.adapters;

import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.databinding.CellCodeBinding
import owlsdevelopers.org.passcoder.domain.models.Passcode


class PasscodeAdapter(private val callback: Callback) : PagingDataAdapter<Passcode, PasscodeAdapter.ViewHolder>(POST_COMPARATOR) {

    companion object {
        const val ITEM_LAYOUT = R.layout.cell_code
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Passcode>() {
            override fun areContentsTheSame(oldItem: Passcode, newItem: Passcode): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Passcode, newItem: Passcode): Boolean =
                    oldItem.value == newItem.value
        }
    }


    inner class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        private val binding = CellCodeBinding.bind(view)
        fun bind(data: Passcode) {
            with(binding) {
                codeValue.text = data.value
                codeDescription.text = data.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(ITEM_LAYOUT, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) as Passcode
        holder.itemView.setOnClickListener { callback.onItemClicked(item) }
        holder.itemView.setOnLongClickListener {
            callback.onItemLongClicked(item)
            true
        }
        holder.bind(item)
    }


    interface Callback {
        fun onItemClicked(item: Passcode)
        fun onItemLongClicked(item: Passcode)
    }

}
