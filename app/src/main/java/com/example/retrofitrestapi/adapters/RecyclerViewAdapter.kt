package com.example.retrofitrestapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrestapi.R
import com.example.retrofitrestapi.User
import kotlinx.android.synthetic.main.recycler_row_list.view.*

open class RecyclerViewAdapter
constructor(
   val OnclickListener: OnItemClickListener
): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_row_list,
                parent,
                false
            )
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            OnclickListener.onItemEditClick(userList[position])
        }
    }

    override fun getItemCount() = userList.size

    interface OnItemClickListener {
        fun onItemEditClick(user: User)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.text_view_name
        private val textViewEmail: TextView = view.text_view_email
        private val textViewStats: TextView = view.text_view_stats

        fun bind(data: User) {
            textViewName.text = data.name
            textViewEmail.text = data.email
            textViewStats.text = data.status
        }
    }
}
