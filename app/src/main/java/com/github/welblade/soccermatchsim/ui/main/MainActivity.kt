package com.github.welblade.soccermatchsim.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.welblade.soccermatchsim.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModel<MainViewModel>()
    private val matchAdapter by lazy { MatchItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        setupListeners()
        setupDataObserver()
        setupMatchesRefresh()
        loadMatchList()
    }

    private fun setupDataObserver() {
        mainViewModel.state.observe(this) {
            when(it){
                MainViewModel.State.Loading -> binding.srlMatches.isRefreshing = true
                is MainViewModel.State.Error -> {
                    binding.srlMatches.isRefreshing = false
                    showErrorMessage(it.error.message.toString())
                }
                is MainViewModel.State.Success -> {
                    binding.srlMatches.isRefreshing = false
                    matchAdapter.submitList(it.list)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvMatches.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = matchAdapter
        }
    }

    private fun setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(this::loadMatchList)
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
        matchAdapter.startSimulation()
    }

    private fun loadMatchList() {
        mainViewModel.getMatches()
    }
}
