package com.example.welcomeaboard.affirmationsRecycleView.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.welcomeaboard.R
import com.example.welcomeaboard.affirmationsRecycleView.model.Affirmation

class AffirmationItemAdapter(
    private val context: Context,
    private val dataSet: List<Affirmation>
    ):RecyclerView.Adapter<AffirmationItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val TextView: TextView = view.findViewById(R.id.item_title)
        val ImageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.affirmation_list_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        val txt = "${position+1}. ${context.resources.getString(item.stringResourceId)}"
        holder.TextView.text = txt
        holder.ImageView.setImageResource(item.drawableResourceId)
    }

    override fun getItemCount() = dataSet.size
}