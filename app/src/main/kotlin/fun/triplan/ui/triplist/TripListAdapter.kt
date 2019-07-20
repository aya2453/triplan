package `fun`.triplan.ui.triplist

import `fun`.triplan.R
import `fun`.triplan.databinding.FragmentTriplistItemBinding
import `fun`.triplan.ui.triplist.TripListAdapter.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class TripListAdapter : RecyclerView.Adapter<ViewHolder>() {

    lateinit var binding: FragmentTriplistItemBinding

    var onTripClicked: (view: View, tripListId: Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentTriplistItemBinding>(inflater, R.layout.fragment_triplist_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.sample.setOnClickListener {
            onTripClicked(it, position)
        }
//        holder.textView.text = "Simple Item ${position + 1}"
    }

    override fun getItemCount(): Int {
        return 20
    }

    class ViewHolder(val binding: FragmentTriplistItemBinding) : RecyclerView.ViewHolder(binding.root)

}