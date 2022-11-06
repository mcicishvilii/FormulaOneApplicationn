package com.example.formulaone.ui.navMenuFragments.drivers.list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.DriversAdapter
import com.example.formulaone.ui.navMenuFragments.drivers.DriversDetails
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.databinding.FragmentDriversBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DriversFragment : BaseFragment<FragmentDriversBinding>(FragmentDriversBinding::inflate) {
    private val driversViewModel: DriversViewModel by viewModels()
    private val driversAdapter: DriversAdapter by lazy { DriversAdapter() }

    override fun viewCreated() {
        driversViewModel.getDrivers()
        observe()
    }

    override fun listeners() {
        toDetails()
    }

    private fun toDetails(){
        driversAdapter.apply {
            setOnItemClickListener{ driver,_ ->
//                Snackbar.make(binding.root,driver.Driver.familyName,Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(DriversFragmentDirections.actionDriversFragmentToDriverDetailsFragment(
                    DriversDetails(
                        driver.Driver.givenName,
                        driver.Driver.familyName,
                        driver.wins,
                        driver.position,
                        driver.Driver.nationality,
                        driver.Driver.dateOfBirth,
                        driver.Driver.permanentNumber,
                        driver.Constructors[0].name,
                    )
                ))
            }
        }
    }

    private fun setupRecycler() {
        binding.rvDrivers.apply {
            adapter = driversAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun observe(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                driversViewModel.state.collectLatest {
                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {
                            binding.tvNowLoading.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            driversAdapter.submitList(it.data.MRData.StandingsTable.StandingsLists[0].DriverStandings)
                            binding.tvNowLoading.visibility = View.INVISIBLE
                        }
                    }
                }
//
//                driversViewModel.detailsState.collectLatest {
//                    when(it){
//                        is Resource.Error -> {
//
//                        }
//                        is Resource.Loading -> {
//                        }
//                        is Resource.Success -> {
//                            it.data[0].Results.
//                        }
//                    }
//                }
            }
        }

    }


}