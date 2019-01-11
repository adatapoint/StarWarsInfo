package com.vince.starwarsinfo.remote

import com.vince.starwarsinfo.model.People
import com.vince.starwarsinfo.model.Planet
import com.vince.starwarsinfo.model.Starship
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


interface ApiService {

    @GET("people/{id}")
    fun getPeopleById(@Path("id") id: Int): Call<People>

    @GET("planets/{id}")
    fun getPlanetById(@Path("id") id: Int): Single<List<Planet>>

    @GET("starships/{id}")
    fun getStarshipById(@Path("id") id: Int): Single<List<Starship>>

//    companion object Factory{
//        fun create(): ApiService {
//            val URL_SWAPI = "https://swapi.co/api/"
//
//            val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl(URL_SWAPI)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            return retrofit.create<ApiService>(ApiService::class.java)
//        }
//    }

}