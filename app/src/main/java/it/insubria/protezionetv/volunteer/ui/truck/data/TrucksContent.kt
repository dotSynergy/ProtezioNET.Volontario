package it.insubria.protezionetv.volunteer.ui.truck.data

import android.widget.Toast
import com.google.firebase.database.*
import it.insubria.protezionet.common.Truck
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object TrucksContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<TruckItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, TruckItem> = HashMap()

    //istanza utilizzata per ottenere un riferimento al nodo del database da cui leggere
    private lateinit var reference: DatabaseReference

    private val COUNT = 25

    init {

        reference = FirebaseDatabase.getInstance().getReference("truck") //rifermento al nodo truck da cui leggere

        //get the data
        reference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for (postSnapshot in snapshot.children) {
                    val truck: Truck? = postSnapshot.getValue(Truck::class.java)
                    if (truck != null) {
                        addItem(createTruckItem(truck))
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun addItem(item: TruckItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createTruckItem(truck: Truck): TruckItem {
        return TruckItem(truck.id, truck.tipo, makeDetails(truck))
    }

    private fun makeDetails(truck: Truck): String {
        val builder = StringBuilder()
        builder.append(truck.colore).append(", ").append(truck.targa)
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class TruckItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}