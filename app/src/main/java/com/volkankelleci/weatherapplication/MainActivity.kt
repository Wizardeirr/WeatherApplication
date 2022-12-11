package com.volkankelleci.weatherapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
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

        viewModel = ViewModelProviders.of(this).get(WeatherFragmentViewModel::class.java)
        viewModel.refreshData()
        var countryName=GET.getString("cityName","")
        city_edit_text.setText(countryName)
        getDataFromInternet()


    }
    fun getDataFromInternet() {

        viewModel.weatherData.observe(this, Observer {
            it?.let {
                linearLayout2.visibility=View.VISIBLE
                hot_view.text =it.current.temperature.toString()
                city_name_text.text = it.location.name
                country_code.text = it.location.country
                humidity.text = it.current.humidity.toString()
                winspeed.text = it.current.windSpeed.toString()
                precip.text = it.current.precip.toString()

                Picasso.get().load("https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png")
                    .into(cloud_image_on_temp);

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