package com.alexkorrnd.pmulab5.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.alexkorrnd.pmulab5.BaseAdapter
import com.alexkorrnd.pmulab5.R
import com.alexkorrnd.pmulab5.data.Task

class TaskAdapter(context: Context, val callback: Callback): BaseAdapter<Task, TaskAdapter.Holder>() {

    interface Callback {
        fun onItemClick(adapterPosition: Int)
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)=
            Holder(inflater.inflate(R.layout.item_task, parent, false)).apply {
                itemView.setOnClickListener { callback.onItemClick(adapterPosition) }
                checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                    val task = items[adapterPosition]
                    task.isCompleted = isChecked
                }
            }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val task = items[position]
        with(holder) {
            tvTitle.text = task.title
            tvDescription.text = task.description
            checkbox.isChecked = task.isCompleted
        }
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle) as TextView
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription) as TextView
        val checkbox: CheckBox = itemView.findViewById(R.id.checkBox) as CheckBox

    }
}
