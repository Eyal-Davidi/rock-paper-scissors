package com.example.madlevel4task2

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.databinding.FragmentGamePlayBinding
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamePlayFragment : Fragment() {

    private var _binding: FragmentGamePlayBinding? = null
    var winCounter: Int=0
    var drawCounter: Int=0
    var loseCounter: Int=0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onScissors()
        onPaper()
        onRock()
    }

    fun updateImages(game : Game){
        binding.imgChosenRight.setImageResource(game.youMove.imgId)
        binding.imgChosenLeft.setImageResource(game.computerMove.imgId)
    }

    fun updateResults(game : Game){
        if (game.gameResult == "WIN") {
            binding.tvGameResult.text = game.gameResult
            winCounter ++
            binding.tvWinResult.text = winCounter.toString()
        }

        if (game.gameResult == "DRAW") {
            binding.tvGameResult.text = game.gameResult
            drawCounter ++
            binding.tvDrawResult.text = drawCounter.toString()
        }

        if (game.gameResult == "LOSE") {
            binding.tvGameResult.text = game.gameResult
            drawCounter ++
            binding.tvLoseResult.text = drawCounter.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onScissors() {
        binding.imgScissors.setOnClickListener {
            val game = Game(
                LocalDateTime.now(),
                GameMove.scissors,
                GameMove.values().random(),
            )

            if (game.computerMove == GameMove.scissors)
                game.gameResult = "DRAW"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "WIN"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "LOSE"

            updateImages(game)
            updateResults(game)
        }
    }

    fun onPaper() {
        binding.imgPaper.setOnClickListener {
            val game = Game(
                LocalDateTime.now(),
                GameMove.paper,
                GameMove.values().random(),
            )

            if (game.computerMove == GameMove.scissors)
                game.gameResult = "LOSE"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "DRAW"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "WIN"

            updateImages(game)
            updateResults(game)
        }
    }

    fun onRock(){
        binding.imgRock.setOnClickListener {
            val game = Game(
                LocalDateTime.now(),
                GameMove.rock,
                GameMove.values().random()
            )
            if (game.computerMove == GameMove.scissors)
                game.gameResult = "WIN"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "LOSE"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "DRAW"

            updateImages(game)
            updateResults(game)
        }
    }
}

