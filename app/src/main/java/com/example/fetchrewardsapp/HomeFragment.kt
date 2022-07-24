package com.example.fetchrewardsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.fetchrewardsapp.databinding.FragmentHomeBinding
import com.example.fetchrewardsapp.viewmodel.FetchViewModel
import kotlinx.coroutines.*


class HomeFragment : Fragment() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val fetchViewModel: FetchViewModel by navGraphViewModels(R.id.nav_graph)

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fetchBtn.setOnClickListener {
            coroutineScope.launch {
                temporarilyLoad(4000)
                fetchViewModel.fetchList()
                lifecycleScope.launch {
                    delay(400)
                    navigateToFetchListFragment()
                }
            }
        }
    }

    fun navigateToFetchListFragment() {
        if (findNavController().currentDestination?.id !=R.id.fragment_fetch) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFetchFragment())
        }
    }

    private fun temporarilyLoad(delayTime: Long) {
        binding.progress.visibility = View.VISIBLE
        binding.fetchBtn.isEnabled = false
        lifecycleScope.launch {
            delay(delayTime)
            disableLoad()
        }
    }



    private fun disableLoad() {
        binding.progress.visibility = View.GONE
        binding.fetchBtn.isEnabled = true
    }



}
