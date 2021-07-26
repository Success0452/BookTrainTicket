package com.famous.booktrainticket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val context: Context, var list:ArrayList<DatabaseModel>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var fullName: TextView = itemView.findViewById(R.id.nameTxt)
        var destination: TextView = itemView.findViewById(R.id.destinationTxt)
        var source: TextView = itemView.findViewById(R.id.sourceTxt)
        var date: TextView = itemView.findViewById(R.id.dateTxt)
        var booking = itemView.findViewById<TextView>(R.id.bookingNumberTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_top, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.fullName.text = list[position].fullName
        holder.destination.text = list[position].destination
        holder.source.text = list[position].source
//        holder.date.text = list[position].date.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}