package com.example.firstapp

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Kolincodes on 10/05/2018.
 */

class SharedPreference(val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun saveString(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, value)

        editor.commit()
    }

    fun retrieveString(KEY_NAME: String): String? {

        return sharedPref.getString(KEY_NAME, "none")
    }

    fun saveInt(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putInt(KEY_NAME, value)

        editor.commit()
    }

    fun retrieveInt(KEY_NAME: String): Int {

        return sharedPref.getInt(KEY_NAME, 0)
    }

    fun saveFloat(KEY_NAME: String, value: Float) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putFloat(KEY_NAME, value)

        editor.commit()
    }

    fun retrieveFloat(KEY_NAME: String): Float {

        return sharedPref.getFloat(KEY_NAME, 0F)
    }


    fun saveCity(id: Int, city: City) {
        saveString(id.toString(), city.name);
        saveFloat(city.name+"temp", city.main.temp)
        saveFloat(city.name+"speed", city.wind.speed)
    }

    fun retrieveCity(id: Int): City {

        var cityname: String? = retrieveString(id.toString())
        if(cityname == null) {

        }

        var main: Main = Main(retrieveFloat(cityname+"temp"), 0.0F, null, null)
        var rain: Rain = Rain(0.0F)
        var clouds: Clouds = Clouds(0)
        var weather: Weather = Weather("No description", "No long description")
        var wind: Wind = Wind(retrieveFloat(cityname+"speed"), 0)
        var city:City = City(retrieveString(id.toString()),main,wind,clouds,weather, rain)

        return city
    }

    fun initCache() {
        saveString("cache", "yes")
    }

    fun checkCache() : Int  {
        var string = retrieveString("cache")
        if(string == "yes") {
            return 1
        } else {
            return 0
        }
    }

}