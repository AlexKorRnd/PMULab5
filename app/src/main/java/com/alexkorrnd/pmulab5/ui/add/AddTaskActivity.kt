package com.alexkorrnd.pmulab5.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.alexkorrnd.pmulab5.R
import com.alexkorrnd.pmulab5.data.Task
import com.alexkorrnd.pmulab5.data.TaskManager

class AddTaskActivity: AppCompatActivity() {

    companion object {
        private const val EXTRA_TASK = "EXTRA_TASK"

        fun getIntent(context: Context) =
                Intent(context, AddTaskActivity::class.java)

        fun unpackTask(intent: Intent): Task = intent.getParcelableExtra(EXTRA_TASK)
    }

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        etTitle = findViewById(R.id.etTitle) as EditText
        etDescription = findViewById(R.id.etDescription) as EditText

        (findViewById(R.id.btnSave) as Button).setOnClickListener {
            val task = Task(etTitle.text.toString(), etDescription.text.toString())
            TaskManager(this).add(task)
            setResult(RESULT_OK,
                    Intent().putExtra(EXTRA_TASK, task)
            )
            finish()
        }
    }


}
