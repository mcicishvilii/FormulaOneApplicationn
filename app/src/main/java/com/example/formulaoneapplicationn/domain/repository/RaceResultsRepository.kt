package com.example.formulaone.domain.repository

import com.example.formulaoneapplicationn.domain.model.RaceDomain

interface RaceResultsRepository {
    suspend fun GetRaceResultsRepository(): List<RaceDomain>
}