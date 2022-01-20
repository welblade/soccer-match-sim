package com.github.welblade.soccermatchsim.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.welblade.soccermatchsim.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        setupDataObserver()
        loadMatchList()
    }

    private fun setupDataObserver() {
        TODO("Not yet implemented")
    }

    private fun setupRecyclerView() {
        TODO("Not yet implemented")
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setupListeners(){
        binding.fabSimulate.setOnClickListener {
            it.animate().rotation(360f).setDuration(200).setListener(
                object: AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        startMatchesSimulation()
                    }
                }
            )
        }
    }

    private fun startMatchesSimulation() {
        TODO("Not yet implemented")
    }

    private fun loadMatchList() {
        TODO("Not yet implemented")
    }
}
