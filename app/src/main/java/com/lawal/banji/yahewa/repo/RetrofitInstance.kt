package com.lawal.banji.yahewa.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val api: RetrofitApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
}

//object RetrofitInstanceGeo {
//    private const val GEO_BASE_URL = "https://api.openweathermap.org/geo/1.0/"
//
//    val api: GeoRetrofitApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(GEO_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(GeoRetrofitApi::class.java)
//    }
//}


