package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.DriverStandingsDto
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val api: RaceService
) : CurrentDriversStandingsRepository {


    override suspend fun getCurrentDriversStanding(): DriverStandingsDto {
        return api.getCurrentStandings("300")
    }
}