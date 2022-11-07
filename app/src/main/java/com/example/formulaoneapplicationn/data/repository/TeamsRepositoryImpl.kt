package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.data.daos.TeamsDao
import com.example.formulaoneapplicationn.data.model.TeamsDtoLocal
import com.example.formulaoneapplicationn.data.model.teams.ToTeamsDomain
import com.example.formulaoneapplicationn.data.model.toModel
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.domain.model.toRoomDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api: RaceService,
    private val teamsDao: TeamsDao
) : TeamsRepository {

    override suspend fun getTeamsData(): List<TeamsDomain> {
        return api.getDriversList().MRData.ConstructorTable.Constructors!!.map{it.ToTeamsDomain()}
    }

    override fun getTeams(): Flow<List<TeamsDomain>> {
        return teamsDao.getAll().map {it.map { it.toModel() }}
    }

    override suspend fun insertTeamIntoDb(team: TeamsDomain) {
        teamsDao.insert(team.toRoomDto())
    }

    override suspend fun deleteTeam(team: TeamsDtoLocal) {
        teamsDao.delete(team)
    }

    override suspend fun deleteAll() {
        teamsDao.deleteAll()
    }
}