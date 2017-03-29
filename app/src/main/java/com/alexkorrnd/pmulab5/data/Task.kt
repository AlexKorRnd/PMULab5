package com.alexkorrnd.pmulab5.data

import android.os.Parcel
import android.os.Parcelable
import biz.growapp.base.extensions.readBoolean
import biz.growapp.base.extensions.writeBoolean
import com.alexkorrnd.pmulab5.MainApp
import com.alexkorrnd.pmulab5.R

class Task(val title: String,
           val description: String,
           var isCompleted: Boolean = false
) : Parcelable {

    val completedStatus: String
        get() =
        if (isCompleted) {
            MainApp.instance.getString(R.string.task_completed)
        } else {
            MainApp.instance.getString(R.string.task_not_completed)
        }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Task> = object : Parcelable.Creator<Task> {
            override fun createFromParcel(source: Parcel): Task = Task(source)
            override fun newArray(size: Int): Array<Task?> = arrayOfNulls(size)
        }

        const val TABLE_NAME = "task_table"
        const val TITLE_FIELD_NAME = "title"
        const val DESCRIPTION_FIELD_NAME = "description"
        const val IS_COMPLETED_FIELD_NAME = "isCompleted"
    }

    constructor(source: Parcel) : this(
            title = source.readString(),
            description = source.readString(),
            isCompleted = source.readBoolean()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(description)
        writeBoolean(isCompleted)
    }
}
