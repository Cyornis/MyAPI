package com.example.myapi

import retrofit2.Response
import  retrofit2.Call
import retrofit2.http.*

interface QuotesApi  {

    @GET("tourist")
    suspend fun getQuotes(@Query("page") pageNumber:Int): Response<TouristResponse>

    @POST("tourist")
    fun postData(@Body touristData: TouristData): Call<TouristData>

    @PUT("tourist/{id}")
    suspend fun putData(@Body touristData: TouristData , @Path("id") id:Int):Response<TouristData>///request to the server

    @DELETE("tourist/{id}")
    suspend fun deleteData(@Path("id") id:Int):Response<TouristData>

}