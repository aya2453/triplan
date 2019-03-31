package `fun`.triplan.ui.triplist

import `fun`.triplan.R
import `fun`.triplan.ui.triplist.TripListAdapter.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TripListAdapter : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_triplist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textView.text = "Simple Item ${position + 1}"
    }

    override fun getItemCount(): Int {
        return 20
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val textView: TextView = view.findViewById(R.id.item)
    }
}