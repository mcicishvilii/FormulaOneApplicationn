package com.example.formulaoneapplicationn.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.formulaoneapplicationn.data.daos.TeamsDao
import com.example.formulaoneapplicationn.data.daos.TicketsDao
//import com.example.formulaone.data.local.models.DriversEntity
import com.example.formulaoneapplicationn.data.model.TeamsDtoLocal
import com.example.formulaoneapplicationn.data.model.TicketsEntity

@Database(entities = [TeamsDtoLocal::class, TicketsEntity::class], version = 8)

abstract class DataBase:RoomDatabase() {
    abstract val teamsDao: TeamsDao
    abstract val ticketsDao: TicketsDao
}