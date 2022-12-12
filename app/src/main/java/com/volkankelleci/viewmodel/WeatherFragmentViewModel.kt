package com.volkankelleci.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.model.WeatherAppModel
import com.volkankelleci.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class WeatherFragmentViewModel : ViewModel() {

    private val weatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()

    val weatherData = MutableLiveData<WeatherAppModel>()
    val errorMessage = MutableLiveData<Boolean>()
    val progressBar = MutableLiveData<Boolean>()

    fun refreshData(cityName: String) {
        getDataFromAPI(cityName)

    }

    private fun getDataFromAPI(cityName:String) {
        progressBar.value = true
        disposable.add(
            weatherAPIService
                .getDataService(cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherAppModel>() {
                    override fun onSuccess(t: WeatherAppModel) {
                        weatherData.value = t
                        errorMessage.value = false
                        progressBar.value = false
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = true
                        progressBar.value = false
                        e.printStackTrace()
                    }
                })
        )

    }


}