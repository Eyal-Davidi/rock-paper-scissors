package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val games = arrayListOf<Game>()
    private val HistoryAdapter = HistoryAdapter(games)

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
        initViews()

    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvHistory.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvHistory.adapter = HistoryAdapter
        //createItemTouchHelper().attachToRecyclerView(binding.rvGameHistory)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}