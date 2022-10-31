package com.example.formulaoneapplicationn.ui.navMenuFragments.schedule.recent

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaone.adapters.SchedulesAdapter.RecentRacesAdapter
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.databinding.FragmentRecentRacesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class RecentRacesFragment :
    BaseFragment<FragmentRecentRacesBinding>(FragmentRecentRacesBinding::inflate) {
    private val myAdapter: RecentRacesAdapter by lazy { RecentRacesAdapter() }
    private val recentRacesViewModel: RecentRacesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun viewCreated() {
        recentRacesViewModel.getDetails()
        Log.d("misho", recentRacesViewModel.getDetails().toString())
        observe()

    }

    override fun listeners() {

    }

    private fun setupRecycler() {
        binding.rvRecentRaces.apply {
            adapter = myAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observe() {
        setupRecycler()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                recentRacesViewModel.state1.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            myAdapter.submitList(it.data)

                            Log.d("sia recent",it.data.last().toString())
                        }

                    }
                }
            }
        }
    }
}
