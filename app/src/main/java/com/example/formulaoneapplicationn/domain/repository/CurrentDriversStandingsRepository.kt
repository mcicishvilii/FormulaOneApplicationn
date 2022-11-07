package com.example.formulaoneapplicationn.domain.repository

import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.DriverStandingsDto

// not using yet. for displaying the full list of current driver standings

interface CurrentDriversStandingsRepository {
    suspend fun getCurrentDriversStanding(): DriverStandingsDto
}