package com.example.formulaone.domain.use_case.drivers

import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CurrentDriversStandingsUseCase @Inject constructor(
    private val repository: CurrentDriversStandingsRepository
){
    operator fun invoke(): Flow<Resource<DriverStandingsDto>> = flow {
        try {
            emit(Resource.Loading(true))
            val winningDriver = repository.getCurrentDriversStanding()
            emit(Resource.Success(winningDriver))
        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected"))
        }
        catch (e: IOException){
            emit(Resource.Error("couldn't reach server"))
        }
    }
}