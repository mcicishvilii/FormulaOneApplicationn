package com.example.formulaoneapplicationn.ui.favorites

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavTeamsUseCase: GetFavTeamsUseCase
) : ViewModel() {


    suspend fun getTeam(): Flow<List<TeamsDomain>> {
        return getFavTeamsUseCase()
    }


}