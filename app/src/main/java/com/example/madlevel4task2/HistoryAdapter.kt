package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.databinding.ItemHistoryBinding
import java.time.LocalDateTime

class HistoryAdapter(private val games: List<Game>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHistoryBinding.bind(itemView)

        fun databind(history: Game) {
           // history.gameDate = LocalDateTime.now()
            //binding.tvGameResult.text = history.gameResult
            binding.imgChosenRight.setImageResource(history.youMove.imgId)
            binding.imgChosenLeft.setImageResource(history.computerMove.imgId)
            //binding.tvGameResult.text = history.gameResult
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
