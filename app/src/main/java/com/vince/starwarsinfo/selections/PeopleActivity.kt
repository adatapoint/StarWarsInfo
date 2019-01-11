package com.vince.starwarsinfo.selections

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.vince.starwarsinfo.R
import com.vince.starwarsinfo.model.People

class PeopleActivity : AppCompatActivity() {

    lateinit var people:AbstractList<People>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

    }
    // people 1 to 88
    // planets 1 to 61
    // starships 1 to 13


}
