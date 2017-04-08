package com.alexkorrnd.pmulab5.ui.list

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.alexkorrnd.pmulab5.R
import com.alexkorrnd.pmulab5.data.Task
import com.alexkorrnd.pmulab5.data.TaskManager
import com.alexkorrnd.pmulab5.ui.add.AddTaskActivity

class MainActivity : AppCompatActivity(), TaskAdapter.Callback {

    companion object {
        private const val REQUEST_ADD = 1
    }

    private lateinit var srlItems: SwipeRefreshLayout
    private lateinit var rvItems: RecyclerView
    private lateinit var fabAdd: FloatingActionButton

    private lateinit var adapter: TaskAdapter

    private lateinit var taskManager: TaskManager

    private val tasks: List<Task>
        get() = taskManager.getTasks()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        srlItems = findViewById(R.id.srlItems) as SwipeRefreshLayout
        rvItems = findViewById(R.id.rvItems) as RecyclerView
        fabAdd = findViewById(R.id.fabAdd) as FloatingActionButton

        srlItems.setOnRefreshListener {
            loadTasks()
        }

        fabAdd.setOnClickListener {
            startActivityForResult(AddTaskActivity.getIntent(this), REQUEST_ADD)
        }

        initRecyclerView()

        taskManager = TaskManager(this)
        loadTasks()
    }

    private fun loadTasks() {
        if (tasks.isEmpty()) {
            createFakeTasks()
        } else {
            onTasksLoaded(taskManager.getTasks())
        }
    }

    private fun onTasksLoaded(tasks: List<Task>) {
        if (srlItems.isRefreshing) {
            adapter.clear()
            srlItems.isRefreshing = false
        }

        adapter.addAll(tasks)
    }

    private fun createFakeTasks() {
        for (i in 1..4) {
            taskManager.add(Task("Задача №$i", ""))
        }
        onTasksLoaded(tasks)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK) {
            return
        }

        when(requestCode) {
            REQUEST_ADD -> {
                val task = AddTaskActivity.unpackTask(data!!)
                adapter.add(task)
            }
        }
    }
}
