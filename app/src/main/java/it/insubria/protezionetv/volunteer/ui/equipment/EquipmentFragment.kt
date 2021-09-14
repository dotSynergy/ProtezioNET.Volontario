package it.insubria.protezionetv.volunteer.ui.equipment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ybq.android.spinkit.SpinKitView
import it.insubria.protezionetv.volunteer.R
import it.insubria.protezionetv.volunteer.ui.equipment.data.EquipmentContent
import it.insubria.protezionetv.volunteer.ui.truck.TruckFragment

/**
 * A fragment representing a list of Items.
 */
class EquipmentFragment : Fragment() {

    private var columnCount = 1
    private lateinit var equipmentAdapter: EquipmentRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("items", EquipmentContent.ITEMS.toString())

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_equipment_list, container, false)

        val viewAdapter = view.findViewById<RecyclerView>(R.id.equipmentList)
        // Set the adapter
        if (viewAdapter is RecyclerView) {
            with(viewAdapter) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                equipmentAdapter = EquipmentRecyclerViewAdapter(EquipmentContent.ITEMS)

                Log.i("adapter", adapter.toString())

            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            view.findViewById<RecyclerView>(R.id.equipmentList).adapter = equipmentAdapter
            view.findViewById<SpinKitView>(R.id.spinner).visibility = View.GONE
        }, 1000)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TruckFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}