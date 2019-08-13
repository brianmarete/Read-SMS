package com.brianmarete.aboutme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {

    val TAG = SmsReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action

        if (intentAction != null) {
            when (intentAction) {
                Telephony.Sms.Intents.SMS_RECEIVED_ACTION ->
                    Log.i(TAG, "SMS Received")
            }
        }
    }
}
