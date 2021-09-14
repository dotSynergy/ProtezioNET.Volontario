package it.insubria.protezionetv.volunteer.ui.equipment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import it.insubria.protezionetv.volunteer.databinding.FragmentEquipmentBinding

import it.insubria.protezionetv.volunteer.ui.equipment.data.EquipmentContent

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class EquipmentRecyclerViewAdapter(
    private val values: List<EquipmentContent.EquipmentItem>
) : RecyclerView.Adapter<EquipmentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentEquipmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
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

    inner class ViewHolder(binding: FragmentEquipmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}