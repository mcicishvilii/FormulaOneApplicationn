package com.example.formulaoneapplicationn.di

import com.example.formulaone.domain.repository.*
import com.example.formulaoneapplicationn.data.repository.*
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// providing repos

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    // using
    abstract fun bindTeamsRepository(
        teamsRepository: TeamsRepositoryImpl
    ): TeamsRepository

    @Binds
    @Singleton
    //not using yet
    abstract fun bindWinningDriverRepository(
        driverStandingsRepositoryImpl: DriverStandingsRepositoryImpl
    ): CurrentDriversStandingsRepository

//    @Binds
//    @Singleton
//    //using for two usecases
//    abstract fun bindCircuitRepository(
//        lastRaceRepoImpl: LastRaceImpl
//    ): LastRaceRepository

    @Binds
    @Singleton
    abstract fun bindRaceScheduleRepository(
        raceScheduleRepositoryImpl: RaceScheduleRepositoryImpl
    ): RacesSheduleRepository

    @Binds
    @Singleton
    abstract fun bindRaceDetailsRepository(
        raceDetailsRepositoryImpl: RaceResultsRepositoryImpl
    ): RaceResultsRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository



}
