package com.vince.starwarsinfo.selections

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vince.starwarsinfo.R
import com.vince.starwarsinfo.R.id.parent
import com.vince.starwarsinfo.model.People

class PeopleAdapter (val people: AbstractList<People>, val context: Context): RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.people_adapter, p0,false))
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: PeopleAdapter.ViewHolder, p1: Int) {
        holder.bindItems(people[p1])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItems(data: People){
            val name: TextView = itemView.findViewById(R.id.name)
            val height: TextView = itemView.findViewById(R.id.height)

            name.text = data.name
            height.text = data.height.toString()

        }

    }

}