package com.volkankelleci.model


data class WeatherAppModel(
    val current: Current,
    val location: Location,
    val request: Request
)