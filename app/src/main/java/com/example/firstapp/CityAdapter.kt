package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.addAll
import java.util.ArrayList
import java.util.List

class CityAdapter(private var values: List<Any>, private var valuesfull: List<Any>, var listener: ((City) -> Unit)? = null)  : RecyclerView.Adapter<CityAdapter.ViewHolder>(),
    Filterable {


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


    fun updateList(values: kotlin.collections.List<City>) {
        this.values = values
        valuesfull = values
        with(valuesfull) {
            clear()
        }
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
        if(city is City) {
            holder.txtHeader.text = city.name
            holder.txtFooter.text = "Temp : " + city.main.temp + " Windspeed : " + city.wind.speed
            holder.itemView.setOnClickListener { listener?.invoke(city) }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return values.size
    }

    override fun getFilter(): Filter {
        return valuesFilter;
    }

    private val valuesFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<City> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                with(filteredList) {
                    addAll(valuesfull)
                }
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in valuesfull) {
                    if(item is City) {
                        if (item.name.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            with(values) {
                clear()
                addAll(results.values as List<*>)
            }
            notifyDataSetChanged()
        }
    }



}




