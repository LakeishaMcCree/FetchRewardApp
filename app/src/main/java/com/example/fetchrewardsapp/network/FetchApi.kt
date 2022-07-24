package com.example.fetchrewardsapp.network

import com.example.fetchrewardsapp.Constants
import com.example.fetchrewardsapp.model.FetchListItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface FetchApi {

    @GET("/hiring.json")

    suspend fun fetchItems(): List<FetchListItem>

    companion object {

       operator fun invoke(): FetchApi {


            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.fetchBaseUrl)
                .build()

            return retrofit.create(FetchApi::class.java)
        }
    }
}