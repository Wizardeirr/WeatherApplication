package com.volkankelleci.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.model.Current
import com.volkankelleci.service.WeatherAPI
import com.volkankelleci.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class WeatherFragmentViewModel:ViewModel() {
    val hot_view=MutableLiveData<List<Current>>()
    val error_text= MutableLiveData<Boolean>()
    val progress_Bar=MutableLiveData<Boolean>()

    private val WeatherAPIService=WeatherAPIService()
    private val disposable= CompositeDisposable()

    fun refreshData(){
        takesToDataFromInternet()
    }
    private fun takesToDataFromInternet(){

        disposable.add(
            WeatherAPIService.takeDatas()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Current>>() {
                    override fun onSuccess(t: List<Current>) {
                        hot_view.value=t
                        error_text.value=false
                        progress_Bar.value=false
                    }
                    override fun onError(e: Throwable) {
                        error_text.value=true
                        progress_Bar.value=false
                        e.printStackTrace()
                    }
                })
        )
    }
}