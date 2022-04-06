package com.example.madlevel4task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.databinding.FragmentHistoryBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val games = arrayListOf<Game>()
    private val historyAdapter = HistoryAdapter(games)

    private lateinit var gameRepository: GameRepository
    ////////
    private val mainScope = CoroutineScope(Dispatchers.Main)


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        getGamesFromDatabase()

        //initRV()
        initViews()
        observeGameResult()

        binding.fabDeleteAll.setOnClickListener {
            removeAllProducts()
        }
    }

    private fun removeAllProducts() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllProducts()
            }
            getGamesFromDatabase()
        }
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvHistory.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvHistory.adapter = historyAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeGameResult() {
        setFragmentResultListener(REQ_GAME_KEY) { _, bundle ->
            bundle.getParcelable<Game>(BUNDLE_GAME_KEY)?.let {
                //////////XXXXX//// val game = parcel?? need to be for the whole class!!
                val game = it
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        gameRepository.insertGame(game)
                    }
                    getGamesFromDatabase()
                }
            } ?: Log.e("GamesFragment", "Request triggered, but empty game text!")
        }
    }
                //games.add(game)
                //historyAdapter.notifyDataSetChanged()

//                gameRepository.insertGame(game)
//                getGamesFromDatabase()
//            } ?: Log.e("GamesFragment", "Request triggered, but empty game text!")
//        }
//    }

    private fun getGamesFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@HistoryFragment.games.clear()
            this@HistoryFragment.games.addAll(games)
            historyAdapter.notifyDataSetChanged()
        }
    }

//        val games = gameRepository.getAllGames()
//        //this@HistoryFragment.games.clear()
//        this@HistoryFragment.games.addAll(games)
//        historyAdapter.notifyDataSetChanged()
  //  }
}