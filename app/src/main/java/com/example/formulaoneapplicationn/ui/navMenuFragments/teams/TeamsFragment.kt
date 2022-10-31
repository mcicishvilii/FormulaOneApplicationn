package com.example.formulaoneapplicationn.ui.navMenuFragments.teams

import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.adapters.ConstructorsAdapter
import com.example.formulaone.R
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.databinding.FragmentTeamsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate) {

    private val constructorsAdapter: ConstructorsAdapter by lazy { ConstructorsAdapter() }
    private val viewModel: TeamsViewModel by viewModels()
    private var filteredList = mutableListOf<TeamsDomain>()


    override fun viewCreated() {
        observe()
        search()

    }

    override fun listeners() {
        addToFavourites()
        binding.tvTeamListHeader.setOnClickListener {
            findNavController().navigate(R.id.favoritesFragment)
        }
    }

    private fun addToFavourites() {
        constructorsAdapter.apply {
            setOnItemClickListener { team, _ ->
                viewModel.insertTeam(team)
                Toast.makeText(requireContext(), "${team.name} added to db", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupRecycler() {
        binding.rvStories.apply {
            adapter = constructorsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun observe() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            constructorsAdapter.submitList(it.data)
                            filteredList = it.data.toMutableList()
                        }
                    }
                }
            }
        }
    }

    private fun search() {
        binding.searchView.doOnTextChanged { text, _, _, _ ->
            Log.d("mcicishvili", text.toString())
            if (!text.isNullOrEmpty()) {
                viewModel.searh(text.toString())
            }
            else{
                constructorsAdapter.submitList(filteredList)
            }
        }
    }


}