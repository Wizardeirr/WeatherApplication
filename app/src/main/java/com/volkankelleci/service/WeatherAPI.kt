package com.volkankelleci.service

import com.volkankelleci.model.Current
import com.volkankelleci.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("current?access_key=20b169b63c72e744bcd6a3d69ee0fdf7")
    //BASE_URL=http://api.weatherstack.com/current?access_key=20b169b63c72e744bcd6a3d69ee0fdf7&query=Malatya

    fun getData(
        @Query("query") name:String
    ):Single<Weather>
}