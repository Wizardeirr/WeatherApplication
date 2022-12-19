package com.volkankelleci.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathertable")
data class WeatherAppModel(
    val current: Current,
    val location: Location,
    val request: Request
){
    @PrimaryKey(autoGenerate = true)
    val uuid:Int =0
}