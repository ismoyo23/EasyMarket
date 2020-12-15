package com.example.easymarkert

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.easymarkert.model.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_product_component.view.*

class myAdapter(private val dataList: MutableList<Data>): RecyclerView.Adapter<myHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        context = parent.context
        return myHolder(LayoutInflater.from(context).inflate(R.layout.list_product_component, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        val data = dataList[position]

        val TitleTextView = holder.itemView.title
        val ImageTextView = holder.itemView.imageProduct

        val Title = "${data.firstName}"
        TitleTextView.text = Title

        Picasso.get()
            .load(data.avatar)
            .into(ImageTextView)

        holder.itemView.setOnClickListener{
            Toast.makeText(context, Title, Toast.LENGTH_SHORT).show()
        }

    }
}




