package com.volkankelleci.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.volkankelleci.model.WeatherAppModel

@Dao
interface WeatherDao {
    @Insert
    suspend fun insertAllWeathers(vararg weather:WeatherAppModel) : List<Long>

    @Query("SELECT*FROM weathertable")
    suspend fun getAllWeathers():List<WeatherAppModel>

    @Query("DELETE  FROM weathertable")
    suspend fun deleteAllWeathers()
}