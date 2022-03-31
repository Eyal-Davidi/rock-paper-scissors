package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.databinding.FragmentGameHistoryBinding
import com.example.madlevel4task2.databinding.ItemGameHistoryBinding
import java.time.LocalDateTime

class GameHistoryAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemGameHistoryBinding.bind(itemView)

        fun databind(gameHistory: Game) {
            gameHistory.gameDate = LocalDateTime.now()
            binding.tvGameResult.text = gameHistory.gameResult
            binding.imgChosenRight.setImageResource(gameHistory.youMove.imgId)
            binding.imgChosenLeft.setImageResource(gameHistory.computerMove.imgId)
            binding.tvGameResult.text = gameHistory.gameResult
        }
    }
    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_history, parent, false)
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
