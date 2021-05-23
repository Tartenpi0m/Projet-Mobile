package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.firstapp.model.City

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CityDetailFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = arguments?.get("cityObject")

    if(city is City) {

        view.findViewById<TextView>(R.id.textview_name).text = city.name
        view.findViewById<TextView>(R.id.textview_main_temp).text = city.main.temp.toString()
        view.findViewById<TextView>(R.id.textview_wind_speed).text = city.wind.speed.toString()
        view.findViewById<TextView>(R.id.textview_main_feelslike).text = city.main.feels_like.toString()
        view.findViewById<TextView>(R.id.textview_clouds_all).text = city.clouds.all.toString()
        view.findViewById<TextView>(R.id.textview_weather_description).text = city.weather.description



    } else {
        view.findViewById<TextView>(R.id.textview_name).text = "Aucune ville sélectionnée"
    }



        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_CityDetailFragment_to_CityListFragment)
        }
    }
}