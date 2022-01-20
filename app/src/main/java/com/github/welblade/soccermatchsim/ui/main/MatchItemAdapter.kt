package com.github.welblade.soccermatchsim.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
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
        for (i in 0 until itemCount) {
            getItem(i).apply{
                home.score = Random.nextInt(0, home.stars)
                away.score = Random.nextInt(0, away.stars)
            }
            notifyItemChanged(i)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(private val itemMatchBinding: ItemMatchBinding):RecyclerView.ViewHolder(itemMatchBinding.root) {
        fun bind(item: Match){
            itemMatchBinding.apply {
                ivHomeTeamFlag.load(item.home.image) {
                    transformations(CircleCropTransformation())
                }
                tvHomeTeam.text = item.home.name
                tvTeam1Score.text = item.home.score.toString()

                ivAwayTeamFlag.load(item.away.image) {
                    transformations(CircleCropTransformation())
                }
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
