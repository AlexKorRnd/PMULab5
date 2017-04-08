package com.alexkorrnd.pmulab5.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.alexkorrnd.pmulab5.data.database.DatabaseHelper
import com.alexkorrnd.pmulab5.data.database.TaskCursorWrapper


class TaskManager(context: Context) {

    companion object {
        private fun getContentValues(task: Task): ContentValues {
            val values = ContentValues()
            values.put(Task.TITLE_FIELD_NAME, task.title)
            values.put(Task.DESCRIPTION_FIELD_NAME, task.description)
            values.put(Task.TITLE_FIELD_NAME, task.title)
            return values
        }

    }

    private val database: SQLiteDatabase = DatabaseHelper(context).writableDatabase

    fun add(task: Task) {
        val values = getContentValues(task)
        database.insert(Task.TABLE_NAME, null, values)
    }

    private fun queryTasks(whereClause: String?, whereArgs: Array<String>?): TaskCursorWrapper {
        val cursor = database.query(
                Task.TABLE_NAME,
                null,
                whereClause,
                whereArgs, null, null, null
        )
        return TaskCursorWrapper(cursor)
    }

    fun getTasks(): List<Task> {
        val tasks = ArrayList<Task>()
        val cursor = queryTasks(null, null)
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                tasks.add(cursor.getTask())
                cursor.moveToNext()
            }
        } finally {
            cursor.close()
        }
        return tasks
    }
}