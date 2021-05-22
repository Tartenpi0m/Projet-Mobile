package com.example.firstapp

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CityListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView;
    private var adapter = CityAdapter(listOf(), ::onClickedCity)
    private lateinit var button_actualiser: Button

    private lateinit var cache:SharedPreference;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)



    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cache=SharedPreference(requireContext())

        button_actualiser = view.findViewById(R.id.button_actualiser)
        button_actualiser.setOnClickListener {
            makeApiCall()
        }

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CityListFragment.adapter
        }

        if(cache.checkCache()) {
            var list=getListFromCache(cache)
            showList(list)
        } else {
            makeApiCall()
        }

    }

    private fun getListFromCache(cache:SharedPreference): List<City> {

        var list: MutableList<City> = ArrayList<City>()
        for(id in 1 until 20) {
            list.add(cache.retrieveCity(id))
        }
        return list
    }

    private fun saveListIntoCache(cache:SharedPreference, list: List<City>) {
        cache.initCache()
        var id = 1
        for(city in list) {
            cache.saveCity(id,city)
            id ++
        }
    }

    private fun showList(CityList: List<City>) {

        adapter.updateList(CityList)
       /*/ recyclerView.setHasFixedSize(true);

        //use a linear layout manager
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.layoutManager = GridLayoutManager(this,  2)
        recyclerView.adapter = mAdapter;*/
    }

    val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private fun makeApiCall() { val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val cityWeatherApi: CityWeatherApi = retrofit.create(CityWeatherApi::class.java)

        val call: Call<AllCityRestResponse> = cityWeatherApi.getAllCityResponse();

        call.enqueue(object: Callback<AllCityRestResponse> {
            override fun onResponse(call: Call<AllCityRestResponse>, response: Response<AllCityRestResponse>) {
                if (response.isSuccessful && response.body() != null) {

                    val allCityRestResponse: AllCityRestResponse = response.body()!!
                    Toast.makeText(context, "API SUCCESS", Toast.LENGTH_SHORT).show();
                    saveListIntoCache(cache, allCityRestResponse.list)
                    adapter.updateList(allCityRestResponse.list)

                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<AllCityRestResponse>, t: Throwable) {
                showError()
            }
        })


    }




    private fun showError() {
        Toast.makeText(context, "API ERROR", Toast.LENGTH_SHORT).show();
    }


    private fun onClickedCity(city: City) {

        findNavController().navigate(R.id.action_CityListFragment_to_CityDetailFragment, bundleOf("cityObject" to city))
    }



}

