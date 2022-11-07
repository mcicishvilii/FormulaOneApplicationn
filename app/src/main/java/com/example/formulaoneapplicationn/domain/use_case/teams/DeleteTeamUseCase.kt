package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaoneapplicationn.data.model.TeamsDtoLocal
import javax.inject.Inject

class DeleteTeamUseCase @Inject constructor(
    private val repository: TeamsRepository
) {
    suspend operator fun invoke(team: TeamsDtoLocal) {
        repository.deleteTeam(team)
    }
}