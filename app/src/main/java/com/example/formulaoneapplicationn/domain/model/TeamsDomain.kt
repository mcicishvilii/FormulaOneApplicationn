package com.example.formulaoneapplicationn.domain.model

import com.example.formulaoneapplicationn.data.model.TeamsDtoLocal

data class TeamsDomain(
    val constructorId: String,
    val name: String?,
    val nationality: String?,
    val url: String?
)

fun TeamsDomain.toRoomDto(): TeamsDtoLocal {
    return TeamsDtoLocal(
        teamId = constructorId,
        teamName = name,
        teamNationality = nationality,
        url = url
    )
}