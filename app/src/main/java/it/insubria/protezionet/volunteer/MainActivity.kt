package it.insubria.protezionet.volunteer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import it.insubria.protezionet.volunteer.databinding.ActivityMainBinding
import it.insubria.protezionet.volunteer.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var chipNavigationBar: ChipNavigationBar
    private var fragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipNavigationBar = findViewById(R.id.bottom_nav_bar)

        chipNavigationBar.setItemSelected(R.id.nav_home, true)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()

        chipNavigationBar.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {

            override fun onItemSelected(i: Int) {
                when (i) {
                    R.id.nav_home -> fragment = HomeFragment()
                    R.id.nav_person -> fragment = SecondFragment()
                    //R.id.nav_event -> fragment = EventFragment()
                    //R.id.nav_truck -> fragment = TruckFragment()
                    //R.id.nav_equipment -> fragment = EquipmentFragment()
                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment!!).commit()
                }
            }
        })


    }
}