package com.vlatrof.customviewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vlatrof.customviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMakeHappy.setOnClickListener {
            binding.face.happinessState = EmotionalFaceView.HAPPINESS_STATE_HAPPY
        }

        binding.btnMakeSad.setOnClickListener {
            binding.face.happinessState = EmotionalFaceView.HAPPINESS_STATE_SAD
        }
    }

}
