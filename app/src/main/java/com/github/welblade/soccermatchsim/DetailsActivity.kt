package com.github.welblade.soccermatchsim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.welblade.soccermatchsim.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private val binding: ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}