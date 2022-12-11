package com.volkankelleci.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.model.Current
import com.volkankelleci.model.Weather
import com.volkankelleci.service.WeatherAPI
import com.volkankelleci.service.WeatherAPIService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class WeatherFragmentViewModel:ViewModel() {

    private val weatherAPIService=WeatherAPIService()
    private val disposable=CompositeDisposable()

    val weatherData=MutableLiveData<Weather>()
    val errorMessage=MutableLiveData<Boolean>()
    val progressBar=MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()

    }
    private fun getDataFromAPI(){
        progressBar.value=true
        disposable.add(
            weatherAPIService
                .getDataService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<Weather>(){
                    override fun onSuccess(t: Weather) {
                        weatherData.value=t
                        errorMessage.value=false
                        progressBar.value=false
                    }
                    override fun onError(e: Throwable) {
                        errorMessage.value=true
                        progressBar.value=false
                        e.printStackTrace()
                    }
                })
        )

    }



}