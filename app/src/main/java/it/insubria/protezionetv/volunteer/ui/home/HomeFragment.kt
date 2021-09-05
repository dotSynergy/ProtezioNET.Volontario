package it.insubria.protezionetv.volunteer.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import it.insubria.protezionetv.common.Event
import it.insubria.protezionetv.volunteer.R
import org.json.JSONObject
import java.util.*
import it.insubria.protezionetv.volunteer.ui.home.MapFragment as HomeMapFragment


class HomeFragment : Fragment() {

    private var rootView: View? = null
    var lastEmergency: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return getPersistentView(inflater, container, savedInstanceState, R.layout.fragment_home)
    }

    fun getPersistentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        layout: Int
    ): View? {
        if (rootView == null) rootView = inflater.inflate(layout, null) else (rootView as ViewGroup).removeView(
            rootView
        )
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseLayout: FrameLayout = requireActivity().findViewById(R.id.fragment_event)
        var lastEmergencyJSON: JSONObject? = null
        //todo: implementare logica per prendere da database l'ultima emergenza dentro la classe common.Event
        buildEvent()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun buildEvent() {

        val emergency = true;
        val emergencyAccepted = false;

        val l: LinearLayout = getView()!!.findViewById(R.id.emergenza_layout)
        l.removeAllViews()

        if (emergency) {

            //istanza mappa
            //altezza della mappa responsiva
            val display: Display = activity?.windowManager!!.defaultDisplay
            var dHeight: Int = display.height / 2
            Log.d("height", display.height.toString())
            val f: FrameLayout = activity!!.findViewById(R.id.map)
            f.layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dHeight)
            val m1: HomeMapFragment = HomeMapFragment().getNewMap(45.100, 9.100);
            childFragmentManager.beginTransaction().add(R.id.map, m1).commit()


            val labels = listOf("Localita':", "Tipo:", "Codice:")
            for (i in labels.indices) {
                if (i > 0) {
                    val line = View(context)
                    line.layoutParams =
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 4)
                    line.setBackgroundColor(Color.rgb(211, 211, 211))
                    l.addView(line)
                }


                //creazione del layout del fragment emergenza
                val label = TextView(context) //label
                val value = TextView(context) //oggetto della risposta
                val layout = LinearLayout(context) //layout contenente label e string
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                layoutParams.setMargins(40, 20, 40, 20)
                layout.layoutParams = layoutParams
                layout.weightSum = 2f
                layout.orientation = LinearLayout.HORIZONTAL
                label.text = labels[i]
                label.setTypeface(null, Typeface.BOLD)
                label.textSize = 25f
                label.layoutParams =
                    LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0F)
                value.setText("text")
                value.textSize = 25f
                value.layoutParams =
                    LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
                layout.addView(label)
                layout.addView(value)
                l.addView(layout)
            }

            if(!emergencyAccepted){
                val cardView: CardView = view!!.findViewById(R.id.accept_emergency)
                cardView.visibility = View.VISIBLE

                val acceptButton: Button = this.view!!.findViewById(R.id.accept_button)
                val refuseButton: Button = this.view!!.findViewById(R.id.refuse_button)

                //TODO: listeners
                acceptButton.setOnClickListener { Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show() }
                refuseButton.setOnClickListener { Toast.makeText(context, "Refused", Toast.LENGTH_SHORT).show() }
            }

        } else {

            val t1 = TextView(context)
            val map: FrameLayout = view!!.findViewById(R.id.map)
            map.removeAllViews()
            map.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)

            t1.text = getString(R.string.no_emergencies)
            t1.textSize = 30f
            t1.gravity = Gravity.CENTER
            t1.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            l.addView(t1)
        }

    }

}