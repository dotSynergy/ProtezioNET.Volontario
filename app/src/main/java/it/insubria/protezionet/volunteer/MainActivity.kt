package it.insubria.protezionet.volunteer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import it.insubria.protezionet.volunteer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setUpTabBar()
    }

    private fun setUpTabBar() {

        binding.bottomNavBar.setOnItemSelectedListener {
            when (it) {
                R.id.nav_home -> binding.textMain.text = "Home"
                R.id.nav_person -> binding.textMain.text = "Person"
                R.id.nav_event -> {
                    binding.textMain.text = "event"
                    //binding.bottomNavBar.showBadge(R.id.nav_settings)
                }
                R.id.nav_truck -> {
                    binding.textMain.text = "Truck"
                    //binding.bottomNavBar.dismissBadge(R.id.nav_settings)
                }
                R.id.nav_equipment -> {
                    binding.textMain.text = "equipment"
                    //binding.bottomNavBar.dismissBadge(R.id.nav_settings)
                }
            }
        }
    }
}