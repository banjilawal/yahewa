//package com.lawal.banji.yahewa
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class ForecastRepository(private val apiKey: String) {
//
//    fun getCurrentWeather(city: String, callback: (OpenWeatherResponse?) -> Unit) {
//        val call = RetrofitInstance.api.getCurrentWeather(city, apiKey)
//        call.enqueue(object : Callback<OpenWeatherResponse> {
//            override fun onResponse(call: Call<OpenWeatherResponse>, response: Response<OpenWeatherResponse>) {
//                if (response.isSuccessful) {
//                    callback(response.body())
//                } else {
//                    callback(null)
//                }
//            }
//
//            override fun onFailure(call: Call<OpenWeatherResponse>, t: Throwable) {
//                callback(null)
//            }
//        })
//    }
//}