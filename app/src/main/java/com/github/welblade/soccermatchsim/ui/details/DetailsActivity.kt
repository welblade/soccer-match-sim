package com.github.welblade.soccermatchsim.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.github.welblade.soccermatchsim.data.model.Match
import com.github.welblade.soccermatchsim.databinding.ActivityDetailsBinding

const val EXTRA_MATCH = "extra_match"
class DetailsActivity : AppCompatActivity() {
    private val binding: ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(EXTRA_MATCH)?.let {
            binding.ivPlace.load(it.place.image)
            supportActionBar!!.title = it.place.name
            binding.tvDescription.text = it.description

            binding.ivHomeTeam.load(it.home.image)
            binding.tvHomeTeamName.text = it.home.name
            binding.rbHomeTeamStars.rating = it.home.stars.toFloat()
            binding.tvHomeTeamScore.text = it.home.score.toString()

            binding.ivAwayTeam.load(it.away.image)
            binding.tvAwayTeamName.text = it.away.name
            binding.rbAwayTeamStars.rating = it.away.stars.toFloat()
            binding.tvAwayTeamScore.text = it.away.score.toString()
        }
    }
}