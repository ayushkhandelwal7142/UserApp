package com.akstudios.userapp.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.akstudios.userapp.MainActivity
import com.akstudios.userapp.R

class SplashScreen : AppCompatActivity() {
    private lateinit var schoolName: TextView
    private lateinit var appType: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        schoolName = findViewById(R.id.schoolName)
        appType = findViewById(R.id.adminApp)
        val schoolNameAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.animation2)
        val appTypeAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.animation1)
        schoolName.startAnimation(schoolNameAnim)
        //appType.startAnimation(appTypeAnim)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed( {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}