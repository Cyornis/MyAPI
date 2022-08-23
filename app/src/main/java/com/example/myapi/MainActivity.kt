package com.example.myapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , ItemClickListener {

    private lateinit var datalist: ArrayList<TouristData>
    private lateinit var adapter: Adapter
    lateinit var progressCircular:ProgressBar
    var pageNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressCircular = findViewById(R.id.progress_circular)
        datalist = ArrayList<TouristData>()
        getDataList()
        adapter = Adapter(datalist, this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = adapter
        //   adapter.notifyDataSetChanged()

        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, AddTourist::class.java)
            startActivity(intent)
            // getDataList()   ///list updated
        }

        val nextButton = findViewById<FloatingActionButton>(R.id.next_button)
        nextButton.setOnClickListener{
            getDataList()
        }
    }

    override fun onResume() {
        super.onResume()
        pageNumber = 1
        datalist.clear()
        getDataList()
        adapter.notifyDataSetChanged()
    }

    fun getDataList() {
        progressCircular.visibility = ProgressBar.VISIBLE
        pageNumber = pageNumber+1   ///switching to next page

        val touristApiInterface = RetrofitHelper.getInstance().create(QuotesApi::class.java)

        GlobalScope.launch {

            val response = touristApiInterface.getQuotes(pageNumber)
            pageNumber = response.body()!!.page.toInt()
            for (touristData in response.body()!!.data) {
                datalist.add(touristData)
            }

//            response.body()!!.data.forEach {
//                datalist.add(it)
//            }
//       datalist.addAll(response.body()!!.data)

            this@MainActivity.runOnUiThread {
                adapter.notifyDataSetChanged()
                progressCircular.visibility = ProgressBar.GONE
            }
        }
    }

    override fun onItemClickListener(position: Int) {
        // Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
        val d = datalist[position]
        val intent = Intent(this, UpdateTourist::class.java)
        intent.putExtra("data", d)
        startActivity(intent)
    }

    override fun onDeleteClickListener(id: Int) {

        val touristApiInterface = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        GlobalScope.launch {
            val response = touristApiInterface.deleteData(id)
            this@MainActivity.runOnUiThread {
                adapter.notifyDataSetChanged()
                Log.d("Ayush",response.body().toString())
                Log.d("Ayush",response.code().toString())
                Log.d("Ayush",response.message())
            }

        }
        getDataList()
    }
}