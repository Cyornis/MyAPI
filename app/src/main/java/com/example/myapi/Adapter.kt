package com.example.myapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val todoList:ArrayList<TouristData>, val itemClickListener: ItemClickListener) : RecyclerView.Adapter<Adapter.ToDoViewHolder> () {

    inner class ToDoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val tourist_location = itemView.findViewById<TextView>(R.id.tourist_location)
        val tourist_id = itemView.findViewById<TextView>(R.id.tourist_id)
        val deleteButton = itemView.findViewById<Button>(R.id.delete_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ToDoViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        return  ToDoViewHolder(inflater.inflate(R.layout.tourist_list,parent,false)

        ) }

    override fun onBindViewHolder(holder: Adapter.ToDoViewHolder, position: Int) {
//        holder.bind(todoList[position])
        holder.name.text = todoList[position].name
        holder.tourist_location.text = todoList[position].location
        holder.tourist_id.text = todoList[position].id.toString()

//
//        holder.name.setOnClickListener{
//            // itemClickListener.onItemClickListener (holder.tourist_id.text.toString().toInt()holder.tourist_location.text,)
//            itemClickListener.onItemClickListener(position)
//        }
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClickListener(position)
        }

        holder.deleteButton.setOnClickListener{
            // itemClickListener.onItemClickListener (holder.tourist_id.text.toString().toInt()holder.tourist_location.text,)
            itemClickListener.onDeleteClickListener(todoList[position].id)
        }
    }

    override fun getItemCount(): Int {
         return  todoList.size
    }
}