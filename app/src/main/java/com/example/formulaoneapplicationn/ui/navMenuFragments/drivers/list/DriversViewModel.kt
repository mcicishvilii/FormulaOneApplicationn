package com.example.formulaone.ui.navMenuFragments.drivers.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.domain.use_case.drivers.CurrentDriversStandingsUseCase
import com.example.formulaone.domain.use_case.schedule.RaceDetailsUseCase
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaoneapplicationn.domain.model.RaceDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DriversViewModel @Inject constructor(
    private val currentDriversStandingsUseCase: CurrentDriversStandingsUseCase,
    private val raceDetailsUseCase: RaceDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<DriverStandingsDto>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    private val _detailsState = MutableStateFlow<Resource<List<RaceDomain>>>(Resource.Loading(false))
    val detailsState = _detailsState.asStateFlow()


    fun getDrivers(){
        currentDriversStandingsUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getRaceResultsDetails(){
        raceDetailsUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _detailsState.value = Resource.Success(result.data)
                is Resource.Error -> _detailsState.value = Resource.Error("woops!")
                is Resource.Loading -> _detailsState.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }
}