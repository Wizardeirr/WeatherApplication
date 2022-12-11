package com.volkankelleci.service

import com.volkankelleci.model.Current
import com.volkankelleci.model.Weather
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherAPI {
    @GET("current?access_key=20b169b63c72e744bcd6a3d69ee0fdf7&query=Malatya")
    //BASE_URL=http://api.weatherstack.com/current?access_key=20b169b63c72e744bcd6a3d69ee0fdf7&query=Malatya
    fun getData():Single<Weather>
}