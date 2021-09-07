package it.insubria.protezionetv.volunteer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import it.insubria.protezionet.common.Person
import kotlinx.android.synthetic.main.fragment_person.*


private val personDBReference = FirebaseDatabase.getInstance().getReference("person")

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PersonFragment : Fragment() {

    //istanza utilizzata per rappresentare un utente di firebase
    private lateinit var user: FirebaseUser
    //istanza utilizzata per ottenere un riferimento al nodo del database da cui leggere
    private lateinit var reference: DatabaseReference

    private lateinit var userID: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_person, container, false)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("person") //rifermento al nodo person da cui leggere
        userID = user.uid
        Toast.makeText(activity, "test "+user.uid, Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        //preleviamo i dati da firebase
        reference.child(userID).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile: Person? = snapshot.getValue(Person::class.java)

                Toast.makeText(activity, "test "+userProfile.toString(), Toast.LENGTH_LONG).show()

                if(userProfile != null){
                    val name: String = userProfile.nome
                    val surname: String = userProfile.cognome
                    val email: String = userProfile.email
                    val ruole: String = userProfile.ruolo


                    greetingsText.text = resources.getString(R.string.welcome) + " " + name
                    //emailAddress.text = email
                    //Name.text = name
                    //Surname.text = surname
                    //Ruole.text = ruole
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Something wrong happened!", Toast.LENGTH_LONG).show()

            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}