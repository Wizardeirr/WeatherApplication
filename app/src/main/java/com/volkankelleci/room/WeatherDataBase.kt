package com.volkankelleci.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.volkankelleci.model.WeatherAppModel

@Database(entities = arrayOf(WeatherAppModel::class), version = 1)
abstract class WeatherDataBase :RoomDatabase(){
    abstract fun weatherDAO():Dao

    companion object{

        @Volatile private var instance:WeatherDataBase?=null
        private val lock=Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance?: makeDataBase(context).also {
                instance=it
            }
        }

        private fun makeDataBase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            WeatherDataBase::class.java,"makeDataBase").build()
    }
}