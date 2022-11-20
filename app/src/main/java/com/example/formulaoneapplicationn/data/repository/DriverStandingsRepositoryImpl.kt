package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.toDomain
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val api: RaceService
) : CurrentDriversStandingsRepository {


    override suspend fun getCurrentDriversStanding(): Flow<Resource<List<DriverStandingDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = api.getCurrentStandings("300")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.MRData.StandingsTable.StandingsLists[0].DriverStandings.map{it.toDomain()}))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}