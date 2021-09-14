package it.insubria.protezionetv.volunteer.ui.truck

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import it.insubria.protezionetv.volunteer.R
import it.insubria.protezionetv.volunteer.databinding.FragmentTruckBinding

import it.insubria.protezionetv.volunteer.ui.truck.data.TrucksContent

/**
 * [RecyclerView.Adapter] that can display a [TruckItem].
 * TODO: Replace the implementation with code for your data type.
 */
class TruckItemRecyclerViewAdapter(
    private val values: List<TrucksContent.TruckItem>
) : RecyclerView.Adapter<TruckItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTruckBinding.inflate(
                LayoutInflater.from(parent.context),
                parent.findViewById(R.id.trucksList),
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.content
        holder.contentView.text = item.details
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentTruckBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}