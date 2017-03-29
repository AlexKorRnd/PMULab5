package com.alexkorrnd.pmulab5

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

class MainActivity : AppCompatActivity(), TaskAdapter.Callback {


    private lateinit var rvItems: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rvItems) as RecyclerView
        TaskRepository.makeFake()
        initRecyclerView()
        adapter.addAll(TaskRepository.tasks)
    }

    private fun initRecyclerView() {
        adapter = TaskAdapter(this, this)
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter
    }

    override fun onItemClick(adapterPosition: Int) {
        val task = adapter.items[adapterPosition]
        Toast.makeText(this,
                "${task.title}\n${task.description}\n${task.completedStatus}",
                Toast.LENGTH_LONG
        ).show()
    }
}
