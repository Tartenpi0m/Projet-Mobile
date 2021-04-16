package com.example.firstapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityWeatherApi {


    @GET("find")
    fun getAllCityResponse(
            @Query("lat") latitude: Float= 48.85F,
            @Query("lon") longitude: Float=2.35F,
            @Query("cnt") countCity: Int=50,
            @Query("appid") appid: String="49cf4283c22a58fad77bc408d46bc430"
            ): Call<AllCityRestResponse>

}