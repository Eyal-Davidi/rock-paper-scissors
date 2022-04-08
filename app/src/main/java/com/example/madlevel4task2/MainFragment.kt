package com.example.madlevel4task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.madlevel4task2.GameMove
import com.example.madlevel4task2.HistoryAdapter
import com.example.madlevel4task2.databinding.FragmentMainBinding
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

const val REQ_GAME_KEY = "req_game"
const val BUNDLE_GAME_KEY = "bundle_game"
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private var result: String =""
    private var imgYou: Int =0
    private var imgComputer: Int =0

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val games = arrayListOf<Game>()
    private val historyAdapter = HistoryAdapter(games)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameRepository = GameRepository(requireContext())
        getGamesFromDatabase()

        onScissors()
        onPaper()
        onRock()
    }

    private fun addGameToHistory(game: Game) {
            mainScope.launch {
                setFragmentResult(REQ_GAME_KEY, bundleOf(Pair(BUNDLE_GAME_KEY, game)))

                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game)
                }
                getGamesFromDatabase()
            }
        }

    private fun updateImages(game : Game){
        binding.imgChosenRight.setImageResource(game.youMove.imgId)
        binding.imgChosenLeft.setImageResource(game.computerMove.imgId)

        imgYou = game.youMove.imgId
        imgComputer = game.computerMove.imgId
    }

    private fun updateResults(game : Game){
        if (game.gameResult == "You win!") {
            binding.tvGameResult.text = game.gameResult
        }

        if (game.gameResult == "Draw") {
            binding.tvGameResult.text = game.gameResult
        }

        if (game.gameResult == "Computer wins!") {
            binding.tvGameResult.text = game.gameResult
        }
        result = game.gameResult
        getGamesFromDatabase()
    }

    private fun getGamesFromDatabase() {
        getWinsCounterFromDatabase()
        getDrawsCounterFromDatabase()
        getLosesCounterFromDatabase()

        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@MainFragment.games.clear()
            this@MainFragment.games.addAll(games)
            historyAdapter.notifyDataSetChanged()
        }
    }

    private fun getWinsCounterFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val winCounters = withContext(Dispatchers.IO) {
                gameRepository.countWins()
            }
            binding.tvWinResult.text = winCounters
        }
    }

    private fun getDrawsCounterFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val drawsCounter = withContext(Dispatchers.IO) {
                gameRepository.countDraws()
            }
            binding.tvDrawResult.text = drawsCounter
        }
    }

    private fun getLosesCounterFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val losesCounter = withContext(Dispatchers.IO) {
                gameRepository.countLoses()
            }
            binding.tvLoseResult.text = losesCounter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onScissors() {
        binding.imgScissors.setOnClickListener {
            val game = Game(
                LocalDateTime.now(),
                GameMove.scissors,
                GameMove.values().random(),
            )

            if (game.computerMove == GameMove.scissors)
                game.gameResult = "Draw"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "You win!"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "Computer wins!"

            updateImages(game)
            updateResults(game)
            addGameToHistory(game)
        }
    }

    private fun onPaper() {
        binding.imgPaper.setOnClickListener {
            val game = Game(
                LocalDateTime.now(),
                GameMove.paper,
                GameMove.values().random(),
            )

            if (game.computerMove == GameMove.scissors)
                game.gameResult = "Computer wins!"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "Draw"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "You win!"

            updateImages(game)
            updateResults(game)
            addGameToHistory(game)
        }
    }

    private fun onRock() {
        binding.imgRock.setOnClickListener {
            val game = Game(
                LocalDateTime.now(),
                GameMove.rock,
                GameMove.values().random()
            )
            if (game.computerMove == GameMove.scissors)
                game.gameResult = "You win!"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "Computer wins!"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "Draw"

            updateImages(game)
            updateResults(game)
            addGameToHistory(game)
        }
    }
}