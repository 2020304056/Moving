package com.example.moving

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moving.databinding.ActivitySplashBinding
import com.example.moving.signIn.SignInActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val splashImage = binding.imageSplash

        Glide.with(this).load(R.raw.splash_background).into(splashImage)

        splashImage.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}