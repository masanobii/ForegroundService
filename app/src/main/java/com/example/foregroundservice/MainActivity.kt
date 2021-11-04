package com.example.foregroundservice


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.foregroundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("debug","onCreate")
        binding.buttonServiceStart.setOnClickListener {
            Log.d("debug","btn Clicked")
            val serviceIntent = Intent(this, ForegroundService::class.java)
            startForegroundService(serviceIntent)
        }
        Log.d("debug","onCreate End")

    }
}