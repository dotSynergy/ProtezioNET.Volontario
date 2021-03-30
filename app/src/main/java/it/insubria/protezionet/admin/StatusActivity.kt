package it.insubria.protezionet.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.insubria.protezionet.R

/**
 *
 *  Prima Activity visualizzata dopo il Login
 *
 **/
class StatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)
    }
}