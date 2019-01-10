package com.vince.starwarsinfo

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_selector.*
import kotlinx.android.synthetic.main.dialog_number_input.*

class SelectorActivity : AppCompatActivity() {

    val START: String = "1"
    val END_PLANETS: String = "61"
    val END_PEOPLE: String = "88"
    val END_STARSHIPS: String = "13"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector)

        btn_people.setOnClickListener { openDialog(END_PEOPLE) }
        btn_planets.setOnClickListener { openDialog(END_PLANETS) }
        btn_starships.setOnClickListener { openDialog(END_STARSHIPS) }
    }

    fun openDialog(end: String){
        val numberSelectDialog : Dialog = Dialog(this)
        numberSelectDialog.setContentView(R.layout.dialog_number_input)

        dialog_start.text = "1"
        dialog_end.text = end
    }


}
