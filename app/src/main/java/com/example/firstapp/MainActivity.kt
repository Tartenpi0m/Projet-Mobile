package com.example.firstapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView;
    private var mAdapter = ListAdapter(listOf());

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        showList()
        makeApiCall()
    }

    private fun showList() {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        //use a linear layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = GridLayoutManager(this,  2)


    }

    val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private fun makeApiCall() {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val cityWeatherApi: CityWeatherApi = retrofit.create(CityWeatherApi::class.java)

        val call: Call<AllCityRestResponse> = cityWeatherApi.getAllCityResponse();

        call.enqueue(object:Callback<AllCityRestResponse>{
            override fun onResponse(call: Call<AllCityRestResponse>, response: Response<AllCityRestResponse>) {
                if (response.isSuccessful && response.body() != null) {

                    val allCityRestResponse: AllCityRestResponse = response.body()!!
                    Toast.makeText(applicationContext, "API ERROR", Toast.LENGTH_SHORT).show();
                    mAdapter.updateList(allCityRestResponse.list)
                    recyclerView.adapter = mAdapter;
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
        Toast.makeText(this, "API ERROR", Toast.LENGTH_SHORT).show();
    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

