package com.example.converter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.converter.R
import com.example.converter.models.HistoryModel
import kotlinx.android.synthetic.main.history_item_template.view.*

class HistoryAdapter (private val activity: Context, private val data: ArrayList<HistoryModel>) :
    RecyclerView.Adapter<HistoryAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.history_item_template, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.history_text.text = data[position].historyText
        holder.itemView.history_id.text = data[position].id + ")"
    }

    inner class Holder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)
}