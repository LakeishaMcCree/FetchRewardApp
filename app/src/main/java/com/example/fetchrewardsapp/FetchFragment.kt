package com.example.fetchrewardsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewardsapp.R
import com.example.fetchrewardsapp.adapter.FetchAdapter
import com.example.fetchrewardsapp.databinding.FragmentFetchBinding
import com.example.fetchrewardsapp.viewmodel.FetchViewModel

class FetchFragment : Fragment() {

    private val fetchViewModel: FetchViewModel by navGraphViewModels(R.id.nav_graph)

    private lateinit var adapter: FetchAdapter

    private lateinit var binding: FragmentFetchBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFetchBinding.inflate(inflater)
        val view = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchViewModel.fetchListItem.observe(viewLifecycleOwner) {fetchItemList ->
            adapter.bindData(fetchItemList)
            binding.progress.visibility = View.GONE
        }

        fetchViewModel.fetchList()
    }
}



