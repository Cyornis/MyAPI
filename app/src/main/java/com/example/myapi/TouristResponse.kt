package com.example.myapi

import com.google.gson.annotations.SerializedName

data class TouristResponse(

    @SerializedName("page")
    val page:String,

    @SerializedName("per_page")
    val per_page:String,

    @SerializedName("totalrecord")
    val totalrecord:String,

    @SerializedName("total_pages")
    val total_pages:String,

    @SerializedName("data")
    val data:ArrayList<TouristData>

)
