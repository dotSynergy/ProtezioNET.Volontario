package it.insubria.protezionet.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import it.insubria.protezionet.LoginActivity
import it.insubria.protezionet.R

/**
 * Activity utilizzata per la visualizzazione dello splash screen prima di entrare nell'activity di login
 *
 * per ulteriori informazioni sulla realizzazione visionare i seguenti link:
 *  https://italiancoders.it/splash-screen-in-android-impariamo-a-crearle-nel-modo-corretto/
 *  https://informaticabrutta.it/launch-splash-screen-android/
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(  //todo Handler è deprecato ma non saprei cosa utilizzare altrimenti
                {
                    // Terminati i 3 secondi di attesa verrà lanciata l'activity
                    // principale e terminata quella di launch
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                },
                3000 // La Launch Screen rimarrà visibile per 3 secondi
        )
    }
}