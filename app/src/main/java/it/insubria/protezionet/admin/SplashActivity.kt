package it.insubria.protezionet.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.insubria.protezionet.LoginActivity
import it.insubria.protezionet.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}