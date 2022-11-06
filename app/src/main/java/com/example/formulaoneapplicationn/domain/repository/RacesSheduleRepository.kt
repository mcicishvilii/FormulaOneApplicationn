package com.example.formulaone.domain.repository

import com.example.formulaoneapplicationn.data.model.weather.WeatherDataDto
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain

interface RacesSheduleRepository {
    suspend fun getRacesShceduleData(): List<RaceScheduleDomain>
    suspend fun getWeatherData(lat:Double,long:Double): WeatherDataDto
}