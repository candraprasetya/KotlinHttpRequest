package com.kardusinfo.kotlinhttprequest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kardusinfo.kotlinhttprequest.model.WeatherResponse
import com.kardusinfo.kotlinhttprequest.network.RetrofitClient
import com.kardusinfo.kotlinhttprequest.network.WeatherApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetData.setOnClickListener{
            getData()
        }
    }

    fun getData() {
        pbLoading.visibility = View.VISIBLE
        val retrofit = RetrofitClient().create()
        val weatherApi = retrofit.create(WeatherApi::class.java)

        val cityName = "Yogyakarta"
        val api_id = "b6907d289e10d714a6e88b30761fae22"

        weatherApi.getCurrentWeather(cityName, api_id).enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {

                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val getCityName = weatherResponse.name
                    val getWeatherIcon = weatherResponse.weather!![0].icon
                    val getWeatherDesc = weatherResponse.weather!![0].description
                    val getWeatherTemp = weatherResponse.main!!.temp!!.toDouble()

                    showData(getCityName, getWeatherDesc, getWeatherIcon, getWeatherTemp)
                }
            }

        })
    }

    fun showData(cityName: String?, condition: String?, icon: String?, temper: Double?) {
        pbLoading.visibility = View.GONE
        tvCityName.text = cityName
        tvCondition.text = condition
        tvTemperature.text = "$temper " + "\u2103"


        val iconUrl = "http://openweathermap.org/img/w/" + icon + ".png"
        Glide.with(this).load(iconUrl).into(imgWheaterIcon)
    }

}
