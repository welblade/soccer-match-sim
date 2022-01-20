package com.github.welblade.soccermatchsim.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.welblade.soccermatchsim.data.model.Match
import com.github.welblade.soccermatchsim.databinding.ItemMatchBinding
import com.github.welblade.soccermatchsim.ui.details.DetailsActivity
import com.github.welblade.soccermatchsim.ui.details.EXTRA_MATCH
import kotlin.random.Random

class MatchItemAdapter: ListAdapter<Match, MatchItemAdapter.ViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMatchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun startSimulation(){
        for (i in 0..itemCount) {
            getItem(i).apply{
                home.score = Random(home.stars).nextInt()
                away.score = Random(away.stars).nextInt()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(private val itemMatchBinding: ItemMatchBinding):RecyclerView.ViewHolder(itemMatchBinding.root) {
        fun bind(item: Match){
            itemMatchBinding.apply {
                ivHomeTeamFlag.load(item.home.image)
                tvHomeTeam.text = item.home.name
                tvTeam1Score.text = item.home.score.toString()

                ivAwayTeamFlag.load(item.away.image)
                tvAwayTeam.text = item.away.name
                tvTeam2Score.text = item.away.score.toString()

                root.setOnClickListener { _->
                    this@ViewHolder.itemView.context.let {
                        it.startActivity(
                            Intent(it,  DetailsActivity::class.java).also { intent ->
                                intent.putExtra(EXTRA_MATCH, item)
                            }
                        )
                    }
                }
            }
        }
    }
}

class DiffCallBack: DiffUtil.ItemCallback<Match>() {
    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.away.name == newItem.away.name &&
                oldItem.away.score == newItem.away.score &&
                oldItem.home.name == newItem.home.name &&
                oldItem.home.score == newItem.home.score &&
                oldItem.place.name == newItem.place.name &&
                oldItem.description == newItem.description
    }

}
