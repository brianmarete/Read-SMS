package com.brianmarete.aboutme

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.provider.Telephony
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    companion object {
        val MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1
    }

    private val smsReceiver = SmsReceiver()
    private val filter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.RECEIVE_SMS),
                    MY_PERMISSIONS_REQUEST_RECEIVE_SMS)
        } else {
            this.registerReceiver(smsReceiver, filter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(smsReceiver)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_RECEIVE_SMS -> {
                if ((grantResults.isNotEmpty() && grantResults[0]
                            == PackageManager.PERMISSION_GRANTED)) {
                    this.registerReceiver(smsReceiver, filter)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

}


