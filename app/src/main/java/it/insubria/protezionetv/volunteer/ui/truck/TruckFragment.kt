package it.insubria.protezionetv.volunteer.ui.truck

import android.opengl.Visibility
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
import android.widget.ArrayAdapter
import com.github.ybq.android.spinkit.SpinKitView
import com.google.firebase.database.*
import it.insubria.protezionetv.volunteer.R
import it.insubria.protezionetv.volunteer.ui.truck.data.TrucksContent

/**
 * A fragment representing a list of Items.
 */
class TruckFragment : Fragment() {

    private var columnCount = 1
    private lateinit var truckAdapter: TruckItemRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("items", TrucksContent.ITEMS.toString())

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_truck_list, container, false)

        val viewAdapter = view.findViewById<RecyclerView>(R.id.trucksList)
        // Set the adapter
        if (viewAdapter is RecyclerView) {
            with(viewAdapter) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                truckAdapter = TruckItemRecyclerViewAdapter(TrucksContent.ITEMS)

                Log.i("adapter", adapter.toString())

            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            view.findViewById<RecyclerView>(R.id.trucksList).adapter = truckAdapter
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