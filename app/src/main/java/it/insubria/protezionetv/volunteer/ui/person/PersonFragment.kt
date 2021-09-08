package it.insubria.protezionetv.volunteer.ui.person

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.insubria.protezionetv.volunteer.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import it.insubria.protezionet.common.Person
import it.insubria.protezionet.common.Team
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
    //istanza utilizzata per ottenere un riferimento al nodo del database delle squadre da cui leggere
    private lateinit var squadsReference: DatabaseReference

    private lateinit var currentUser: Person

    private lateinit var teamsAdapter: ArrayAdapter<String>


    private lateinit var userID: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_person, container, false)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("person") //rifermento al nodo person da cui leggere
        userID = user.uid

        squadsReference = FirebaseDatabase.getInstance().getReference("team")

        val squads: ArrayList<String> = ArrayList()

        // Inflate the layout for this fragment
        //preleviamo i dati da firebase
        reference.child(userID).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile: Person? = snapshot.getValue(Person::class.java)

                if(userProfile != null){
                    currentUser = userProfile
                    val name: String = userProfile.nome
                    val surname: String = userProfile.cognome
                    val email: String = userProfile.email
                    val ruole: String = userProfile.ruolo


                    greetingsText.text = resources.getString(R.string.welcome) + " " + name
                    emailAddress.text = email
                    nameText.text = name
                    surnameText.text = surname
                    roleText.text = ruole


                    squadsReference.addValueEventListener(object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {

                            for (postSnapshot in snapshot.children) {
                                val squad: Team? = postSnapshot.getValue(Team::class.java)

                                if(squad!!.utenti.contains(currentUser))
                                    squads.add(squad.nomeSquadra)

                                Log.i("log:: ", squad.nomeSquadra)
                            }

                            teamsAdapter.notifyDataSetChanged()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "Something wrong happened!", Toast.LENGTH_LONG).show()

                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Something wrong happened!", Toast.LENGTH_LONG).show()

            }
        })

        teamsAdapter = ArrayAdapter(requireContext(), R.layout.team_list_row, squads)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        teamsList.adapter = teamsAdapter
    }

}