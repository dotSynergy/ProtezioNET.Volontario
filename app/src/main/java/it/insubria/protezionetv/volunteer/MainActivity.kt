package it.insubria.protezionetv.volunteer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import it.insubria.protezionetv.volunteer.ui.home.HomeFragment
import it.insubria.protezionetv.volunteer.R
import it.insubria.protezionetv.volunteer.ui.equipment.EquipmentFragment
import it.insubria.protezionetv.volunteer.ui.person.PersonFragment
import it.insubria.protezionetv.volunteer.ui.truck.TruckFragment

class MainActivity : AppCompatActivity() {

    private lateinit var chipNavigationBar: ChipNavigationBar
    private var fragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipNavigationBar = findViewById(R.id.chipNavigation)

        chipNavigationBar.setItemSelected(R.id.nav_home, true)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()

        chipNavigationBar.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {

            override fun onItemSelected(i: Int) {
                when (i) {
                    R.id.nav_home -> fragment = HomeFragment()
                    R.id.nav_person -> fragment = PersonFragment()
                    R.id.nav_truck -> fragment = TruckFragment()
                    R.id.nav_equipment -> fragment = EquipmentFragment()
                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment!!).commit()
                }
            }
        })


    }
}