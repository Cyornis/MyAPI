package com.example.myapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TouristData(
    @Expose
    @SerializedName("id")
    var id:Int,

    @Expose
    @SerializedName("tourist_name")
    var name:String,

    @Expose
    @SerializedName("tourist_location")
    var location:String,

    @Expose
    @SerializedName("tourist_email")
    var email:String="anjali@gmail.com",

    @Transient //this ignores the property of conversion from json to object and vice-versa
    @SerializedName("createdat")
    var date:String=""

):Serializable   //it means--can be send from one class to another
