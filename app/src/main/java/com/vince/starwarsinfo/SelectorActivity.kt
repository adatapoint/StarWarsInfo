package com.vince.starwarsinfo

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.vince.starwarsinfo.model.People
import com.vince.starwarsinfo.remote.ApiService
import com.vince.starwarsinfo.selections.InfoPresenter
import com.vince.starwarsinfo.selections.PeopleActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_selector.*
import kotlinx.android.synthetic.main.dialog_number_input.*
import kotlinx.android.synthetic.main.people_adapter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SelectorActivity : AppCompatActivity() {

    //    val infoPresenter: InfoPresenter()
    lateinit var apiService: ApiService

    val START: String = "1"
    val END_PLANETS: String = "61"
    val END_PEOPLE: String = "88"
    val END_STARSHIPS: String = "13"

    lateinit var numberSelectDialog: Dialog
    lateinit var dialog_limit_start: TextView
    lateinit var dialog_limit_end: TextView
    lateinit var dialog_number_picker: NumberPicker
    lateinit var dialog_selected_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector)

        btn_people.setOnClickListener { openDialog(END_PEOPLE) }
        btn_planets.setOnClickListener { openDialog(END_PLANETS) }
        btn_starships.setOnClickListener { openDialog(END_STARSHIPS) }


        val URL_SWAPI = "https://swapi.co/api/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_SWAPI)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create<ApiService>(ApiService::class.java)


    }

    fun startPeopleActivity(people: People?){
        val intent = Intent(applicationContext,PeopleActivity::class.java)
        intent.putExtra("people", people)
        startActivity(intent)
    }

    fun openDialog(end: String) {
        numberSelectDialog = Dialog(this)
        numberSelectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        numberSelectDialog.setContentView(R.layout.dialog_number_input)

        numberSelectDialog.setTitle("Escoge un numero")

        dialog_limit_start = numberSelectDialog.findViewById(R.id.dialog_start) as TextView
        dialog_limit_end = numberSelectDialog.findViewById(R.id.dialog_end) as TextView
        dialog_number_picker = numberSelectDialog.findViewById(R.id.numberPicker) as NumberPicker
        dialog_selected_btn = numberSelectDialog.findViewById(R.id.selected_number) as Button

        dialog_number_picker.minValue = 1
        dialog_number_picker.maxValue = end.toInt()
        dialog_number_picker.wrapSelectorWheel = false

        dialog_limit_start.isEnabled = true
        dialog_limit_end.isEnabled = true

        dialog_limit_start.text = "1"
        dialog_limit_end.text = end

        numberSelectDialog.show()

        dialog_selected_btn.setOnClickListener {
            Toast.makeText(this, "Seleccionado el " + dialog_number_picker.value, Toast.LENGTH_SHORT).show()
            progress_bar.visibility = View.VISIBLE
            numberSelectDialog.hide()
            var response = apiService.getPeopleById(dialog_number_picker.value).enqueue(object : Callback<People> {
                override fun onResponse(
                    call: Call<People>?, response: Response<People>?
                ) {
                    val people = response?.body()
                    Toast.makeText(applicationContext, "No falló!", Toast.LENGTH_SHORT).show()
                    Log.i("qwer", Gson().toJson(people))
                    startPeopleActivity(people)
                }

                override fun onFailure(call: Call<People>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "Falló", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }
}
