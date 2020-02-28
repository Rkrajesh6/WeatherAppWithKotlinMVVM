package com.example.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.ListItem

/**
 * Created by rajeshkumar07 on 28-02-2020.
 */
class MainAdapter(val context: Context, list: List<ListItem>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    val mContext = context
    var mList = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_recyclerview, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = mList[position]
        val temprature = item.main.humidity.toString()+mContext.getString(R.string.temp)
        holder.tvTeprature.text = temprature
        val humadity = mContext.getString(R.string.humidity_label) + item.main.humidity.toString()
        val wind = mContext.getString(R.string.wind_label) + item.wind.deg.toString()
        holder.tvHumadity.text = humadity
        holder.tvWind.text = wind
    }

    fun update(list: List<ListItem>) {
        mList = list
        notifyDataSetChanged()
    }
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTeprature: TextView = itemView.findViewById(R.id.tvTeprature)
        val tvHumadity: TextView = itemView.findViewById(R.id.tvHumadity)
        val tvWind: TextView = itemView.findViewById(R.id.tvWind)
    }
}