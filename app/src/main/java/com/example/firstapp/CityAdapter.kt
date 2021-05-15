package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R

class CityAdapter(private var values: List<City>, var listener: ((City) -> Unit)? = null) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {


        // each data item is just a string in this case
        val txtHeader: TextView
        val txtFooter: TextView

        init {
            txtHeader = view.findViewById<View>(R.id.firstLine) as TextView
            txtFooter = view.findViewById<View>(R.id.secondLine) as TextView
        }
    }

    fun updateList(values: List<City>) {
        this.values = values
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.row_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val city = values[position]
        holder.txtHeader.text = city.name
        holder.txtFooter.text = "Temp : " + city.main.temp + " Windspeed : " + city.wind.speed
        holder.itemView.setOnClickListener { listener?.invoke(city) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return values.size
    }
}
