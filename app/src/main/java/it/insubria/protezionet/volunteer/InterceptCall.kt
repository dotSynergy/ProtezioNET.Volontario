package it.insubria.protezionet.volunteer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast
import java.lang.Exception

class InterceptCall : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING, ignoreCase = true)) {
                Toast.makeText(context, "Ringing!", Toast.LENGTH_SHORT).show()
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK, ignoreCase = true)) {
                Toast.makeText(context, "Recieved!", Toast.LENGTH_SHORT).show()
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE, ignoreCase = true)) {
                Toast.makeText(context, "Idle!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}