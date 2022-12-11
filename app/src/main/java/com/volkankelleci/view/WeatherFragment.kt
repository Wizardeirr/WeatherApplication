package com.volkankelleci.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.volkankelleci.model.Current
import com.volkankelleci.model.Weather
import com.volkankelleci.viewmodel.WeatherFragmentViewModel
import com.volkankelleci.weatherapplication.R
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(WeatherFragmentViewModel::class.java)
        viewModel.refreshData()
        return inflater.inflate(R.layout.fragment_weather, container, false)



    }

    fun getDataFromInternet() {

        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            it?.let {
                linearLayout2.visibility=View.VISIBLE
                hot_view.text=it.current.temperature.toString()
                city_name_text.text=it.location.name
                country_code.text=it.location.country
            }
        })

}
}