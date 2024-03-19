package com.example.findgithub.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.findgithub.R

class Splash : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // Waktu tampilan splash screen (milidetik)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Mendapatkan referensi TextView dari layout
        val ivRandom: ImageView = findViewById(R.id.ivRandom )

        // Membuat animasi fade in
        val fadeInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        ivRandom.startAnimation(fadeInAnimation) // Memulai animasi

        // Setelah waktu SPLASH_TIME_OUT, splash screen akan berakhir dan akan dialihkan ke MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}



