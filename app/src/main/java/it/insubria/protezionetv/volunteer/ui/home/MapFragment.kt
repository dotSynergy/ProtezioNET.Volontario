package it.insubria.protezionetv.volunteer.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.insubria.protezionetv.volunteer.R
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLng
import java.util.*

class MapFragment : Fragment(), OnMapReadyCallback {

    private var lat: Double? = 45.1
    private var lng: Double? = 9.1
    private var latLng: LatLng? = null
    private var mMap: GoogleMap? = null
    private var marker: Marker? = null


    fun getNewMap(
        lat: Double,
        lng: Double
    ): MapFragment {
        val args = Bundle()
        this.lat = lat
        this.lng = lng
        args.putDouble("lat", lat)
        args.putDouble("lng", lng)
        val fragment: MapFragment =
            MapFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)
        setUpMapIfNeeded()
        return view
    }


    private fun setUpMapIfNeeded() {
        if (mMap == null) {
            val mapFrag =
                childFragmentManager.findFragmentById(R.id.small_map) as SupportMapFragment?
            mapFrag?.getMapAsync(this)
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        setUpMap()
    }


    private fun setUpMap() {

        latLng = LatLng(lat!!, lng!!)
        marker?.remove()
        val markerOptions = MarkerOptions().position(latLng!!).icon(
            BitmapDescriptorFactory.fromBitmap(
                Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(
                        resources, resources.getIdentifier(
                            "marker", "drawable", activity?.packageName
                        )
                    ), 50, 90, false
                )
            )
        )
        marker = mMap!!.addMarker(markerOptions)
        mMap!!.setOnMarkerClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q=$lat,$lng")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) requireContext().startActivity(
                mapIntent
            )
            false
        }
        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15.0f).build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap!!.animateCamera(cameraUpdate)
        val prefs = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
        val darkTheme = prefs?.getBoolean("theme", false)
        if (darkTheme!!) {
            val style = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle_night)
            mMap!!.setMapStyle(style)
        }
        mMap!!.uiSettings.isScrollGesturesEnabled = false
    }
}