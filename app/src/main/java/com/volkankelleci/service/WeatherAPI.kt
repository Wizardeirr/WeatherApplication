package com.volkankelleci.service

import com.volkankelleci.model.WeatherAppModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("current?access_key=3268eb81ec16e6f1542c79dc3c7bad12&")
    //BASE_URL=http://api.weatherstack.com/current?access_key=3268eb81ec16e6f1542c79dc3c7bad12&query=Malatya

    fun getData(@Query("query")cityName:String):Single<WeatherAppModel>

    /*
    @GET("api/users")
Call<VeriListem> verilerimilistele(@Query("page") int deger);
     */
}