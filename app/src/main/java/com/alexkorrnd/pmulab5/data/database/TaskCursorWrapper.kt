package com.alexkorrnd.pmulab5.data.database

import android.database.Cursor
import android.database.CursorWrapper
import com.alexkorrnd.pmulab5.data.Task

class TaskCursorWrapper(cursor: Cursor): CursorWrapper(cursor) {

    fun getTask(): Task {
        val title = getString(getColumnIndex(Task.TITLE_FIELD_NAME))
        val description = getString(getColumnIndex(Task.DESCRIPTION_FIELD_NAME))
        val isCompleted = getInt(getColumnIndex(Task.IS_COMPLETED_FIELD_NAME)) != 0

        return Task(title, description, isCompleted)
    }

}
