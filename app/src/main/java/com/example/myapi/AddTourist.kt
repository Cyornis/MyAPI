package com.example.myapi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.IDN
import kotlin.properties.Delegates

class AddTourist : AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var id:EditText
    private lateinit var location:EditText
    private lateinit var email:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_tourist)

         name = findViewById<EditText>(R.id.name_et)
         id = findViewById<EditText>(R.id.tourist_id_et)
         location = findViewById<EditText>(R.id.tourist_location_et)
         email = findViewById<EditText>(R.id.tourist_email_et)
        val add  = findViewById<Button>(R.id.add)

        add.setOnClickListener {

            addData()
        }
    }

    fun addData() {

        val touristData = TouristData(id.text.toString().toInt(),name.text.toString(),location.text.toString(),email.text.toString())
        val touristApiInterface  = RetrofitHelper.getInstance().create(QuotesApi::class.java)

           touristApiInterface.postData(touristData).enqueue(
               object : Callback<TouristData> {

                   override fun onFailure(call: Call<TouristData>, t: Throwable) {
                       Log.d("ayush", "failed")
                   }

                   override fun onResponse(
                       call: Call<TouristData>,
                       response: Response<TouristData>) {

                       val addedUser = response.body()
                       Log.d("ayush", "successful")
                       Log.d("ayush", response.body().toString())
                       Log.d("ayush", response.code().toString())
                       Log.d("ayush", response.message().toString())
                       Log.d("ayush", call.toString())

                   }
               }
           )
       }
    }
