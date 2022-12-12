package com.volkankelleci.viewmodel

import androidx.lifecycle.ViewModel
import com.volkankelleci.service.WeatherAPIService
import io.reactivex.disposables.CompositeDisposable

class detailactivitiyViewModel:ViewModel() {
    private val weatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()

}