package com.example.madlevel4task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.madlevel4task2.databinding.FragmentMainBinding
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
    private var winCounter: Int=0
    private var drawCounter: Int=0
    private var loseCounter: Int=0
    private var result: String =""
    private var imgYou: Int =0
    private var imgComputer: Int =0

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    val games = arrayListOf<Game>()
    val historyAdapter = HistoryAdapter(games)

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

    private fun addGame(game: Game) {
            mainScope.launch {
                //use parcel
                setFragmentResult(REQ_GAME_KEY, bundleOf(Pair(BUNDLE_GAME_KEY, game)))
                //val game = Game(gameDate = LocalDateTime.now(), gameResult = result, youMove =imgYou )
//
//                val game = Game(gameDate = LocalDateTime.now(),
//                        game.gameResult = result,
//                        game.youMove.imgId = imgYou,
//                        game.computerMove.imgId = imgComputer
//                )

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
            loseCounter ++
            binding.tvLoseResult.text = loseCounter.toString()
        }
        result = game.gameResult
    }

//    /////INSERT GAMES HERE
//    private fun updateGameHistory (game:Game){
//        /*
//        game.gameDate = LocalDateTime.now()
//        game.gameResult = result
//        game.youMove.imgId = imgYou
//        game.computerMove.imgId = imgComputer
//         */
//
//        //setFragmentResult(REQ_GAME_KEY, bundleOf(Pair(BUNDLE_GAME_KEY, game)))
//        getGamesFromDatabase()
//    }

    private fun getGamesFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@MainFragment.games.clear()
            this@MainFragment.games.addAll(games)
            historyAdapter.notifyDataSetChanged()
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
                game.gameResult = "DRAW"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "WIN"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "LOSE"

            updateImages(game)
            updateResults(game)
            addGame(game)
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
                game.gameResult = "LOSE"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "DRAW"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "WIN"

            updateImages(game)
            updateResults(game)
            addGame(game)
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
                game.gameResult = "WIN"
            if (game.computerMove == GameMove.paper)
                game.gameResult = "LOSE"
            if (game.computerMove == GameMove.rock)
                game.gameResult = "DRAW"

            updateImages(game)
            updateResults(game)
            addGame(game)
        }
    }
}

