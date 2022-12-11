package com.volkankelleci.weatherapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.get
import com.volkankelleci.model.Weather
import com.volkankelleci.viewmodel.WeatherFragmentViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherFragmentViewModel
    private lateinit var GET:SharedPreferences
    private lateinit var SET:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GET=getSharedPreferences(packageName, MODE_PRIVATE)
        SET=GET.edit()

        var countryName=GET.getString("cityName","")
        city_edit_text.setText(countryName)


        viewModel = ViewModelProviders.of(this).get(WeatherFragmentViewModel::class.java)
        viewModel.refreshData(countryName!!)
        getDataFromInternet()

        swipeRefreshLayout.setOnRefreshListener {
            progress_Bar.visibility=View.VISIBLE
            error_text.visibility=View.GONE
            linearLayout2.visibility=View.GONE


            var cityName=GET.getString("cityName",countryName)
            city_edit_text.setText(cityName)
            viewModel.refreshData(cityName!!)
            swipeRefreshLayout.isRefreshing=false

        }
        search_button.setOnClickListener{
            val cityName=city_edit_text.text.toString()
            SET.putString("cityName",cityName)
            SET.apply()
            viewModel.refreshData(cityName)
            getDataFromInternet()
        }
        getDataFromInternet()


    }
    fun getDataFromInternet() {

        viewModel.weatherData.observe(this, Observer {
            it?.let {
                linearLayout2.visibility=View.VISIBLE
                hot_view.text ="${it.current.temperature.toString()}°C"
                city_name_text.text = it.location.name
                country_code.text = it.location.country
                humidity.text = "Nem % ${it.current.humidity.toString()}"
                winspeed.text = "Rüzgar Hızı ${it.current.windSpeed.toString()}km/s"
                precip.text = "Yağmur % ${it.current.precip.toString()}"
                observation_time.text="Güncellenme Zamanı ${it.current.observationTime}"

                Glide.with(this).load("http://openweathermap.org/img/wn/"+ it.current.weatherIcons.get(0) + "@2x.png")
                    .into(cloud_image_on_temp)


            }
        })
        viewModel.errorMessage.observe(this, Observer {
            if (it) {
                linearLayout2.visibility = View.GONE
                error_text.visibility = View.VISIBLE
                progress_Bar.visibility = View.GONE

            } else {
                error_text.visibility = View.GONE

            }
        })
        viewModel.progressBar.observe(this, Observer {
            if (it) {
                linearLayout2.visibility = View.GONE
                error_text.visibility = View.GONE
                progress_Bar.visibility = View.VISIBLE

            } else {
                progress_Bar.visibility = View.GONE

            }
        })

    }
}