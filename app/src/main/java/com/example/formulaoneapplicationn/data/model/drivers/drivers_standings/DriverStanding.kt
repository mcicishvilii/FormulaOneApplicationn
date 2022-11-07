package com.example.formulaoneapplicationn.data.model.drivers.drivers_standings

import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.Constructor
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.Driver

data class DriverStanding(
    val Constructors: List<Constructor>,
    val Driver: Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)