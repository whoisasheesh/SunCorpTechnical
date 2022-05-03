package com.example.suncorptechnicalchallenge.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.suncorptechnicalchallenge.R


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        if (hasInternet(this)) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(applicationContext, AstronautListingActivity::class.java))
                finish()
            }, 3000)
        } else {
            Toast.makeText(
                applicationContext,
                "Oxygen not enough for space. Please check you internet collection to gather some oxygen for space",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun hasInternet(a: Activity): Boolean {
        var hasConnectedWifi = false
        var hasConnectedMobile = false
        try {
            val cm = a.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals(
                        "wifi",
                        ignoreCase = true
                    )
                ) if (ni.isConnected) hasConnectedWifi = true
                if (ni.typeName.equals(
                        "mobile",
                        ignoreCase = true
                    )
                ) if (ni.isConnected) hasConnectedMobile = true
            }
            return hasConnectedWifi || hasConnectedMobile
        } catch (ex: Exception) {

        }
        return false
    }
}

