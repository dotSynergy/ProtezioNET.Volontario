package it.insubria.protezionetv.volunteer.ui.equipment.data

import com.google.firebase.database.*
import it.insubria.protezionet.common.Equipment
import it.insubria.protezionetv.volunteer.R
import java.util.*
import kotlin.coroutines.coroutineContext

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object EquipmentContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<EquipmentItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, EquipmentItem> = HashMap()

    //istanza utilizzata per ottenere un riferimento al nodo del database da cui leggere
    private lateinit var reference: DatabaseReference

    private val COUNT = 25

    init {

        reference = FirebaseDatabase.getInstance().getReference("equipment") //rifermento al nodo truck da cui leggere

        //get the data
        reference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for (postSnapshot in snapshot.children) {
                    val eq: Equipment? = postSnapshot.getValue(Equipment::class.java)
                    if (eq != null) {
                        addItem(createEquipmentItem(eq))
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun addItem(item: EquipmentItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createEquipmentItem(eq: Equipment): EquipmentItem {
        return EquipmentItem(eq.id, eq.tipo, makeDetails(eq))
    }

    private fun makeDetails(eq: Equipment): String {
        val builder = StringBuilder()
        builder.append("Wear"
        ).append(": ").append(eq.usura)
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class EquipmentItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}