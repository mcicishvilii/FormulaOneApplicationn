package com.example.formulaone.domain.repository

import com.example.formulaoneapplicationn.data.model.TeamsEntity
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import kotlinx.coroutines.flow.Flow

interface TeamsRepository {
//    suspend fun getTeamsData(): List<TeamsDomain>

    fun getTeams(): Flow<List<TeamsDomain>>

    suspend fun insertTeamIntoDb(team: TeamsDomain)

    suspend fun deleteTeam(team: TeamsEntity)

    suspend fun deleteAll()
}