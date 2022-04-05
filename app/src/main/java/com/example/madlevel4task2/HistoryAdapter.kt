package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.databinding.ItemHistoryBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HistoryAdapter(private val games: List<Game>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHistoryBinding.bind(itemView)

        fun databind(game: Game) {
            val formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy")
            binding.tvGameDateHistory.text = formatter.format(game.gameDate)
            binding.tvGameResultHistory.text = game.gameResult
            binding.imgChosenRightHistory.setImageResource(game.youMove.imgId)
            binding.imgChosenLeftHistory.setImageResource(game.computerMove.imgId)
        }
    }
    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }
    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return games.size
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }
}
