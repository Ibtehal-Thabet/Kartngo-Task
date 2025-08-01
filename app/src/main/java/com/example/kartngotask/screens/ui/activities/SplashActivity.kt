package com.example.kartngotask.screens.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kartngotask.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startLoginActivity()
    }

    private fun startLoginActivity() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }, 2000)
    }
}