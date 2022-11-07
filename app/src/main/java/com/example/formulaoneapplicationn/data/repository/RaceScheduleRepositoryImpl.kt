package com.example.formulaoneapplicationn.data.repository

import com.example.formulaone.data.model.raceSchedule.ToRaceScheduleDomain
import com.example.formulaoneapplicationn.data.model.weather.WeatherDataDto
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import com.example.formulaone.domain.repository.RacesSheduleRepository
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.data.services.WeatherService
import javax.inject.Inject

class RaceScheduleRepositoryImpl @Inject constructor(
    private val api: RaceService,
    private val weatherApi: WeatherService
): RacesSheduleRepository {
    override suspend fun getRacesShceduleData(): List<RaceScheduleDomain> {
        return api.getLastRacesSchedule().MRData.RaceTable.Races.map { it.ToRaceScheduleDomain() }
    }


    override suspend fun getWeatherData(lat:Double, long:Double): WeatherDataDto {
//        val response =  weatherApi.getForecast(-23.7036,-46.6997,listOf("temperature_2m_max","precipitation_sum"),"Africa/Cairo")
        val response =  weatherApi.getForecast(lat,long,listOf("temperature_2m_max","precipitation_sum","weathercode"),"Europe/Moscow")
//        Log.d("erorbodi", response1.body()?.daily.toString())
        try {
            return response.body()!!
        }catch (e:Throwable){
            return response.body()!!
        }



    }


}


