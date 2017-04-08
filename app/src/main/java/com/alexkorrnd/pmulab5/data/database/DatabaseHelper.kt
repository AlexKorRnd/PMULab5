package com.alexkorrnd.pmulab5.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alexkorrnd.pmulab5.data.Task


class DatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        private val VERSION = 1
        private val DATABASE_NAME = "dataBase.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + Task.TABLE_NAME + "(" +
                " _id integer primary key autoincrement, " +
                Task.TITLE_FIELD_NAME + ", " +
                Task.DESCRIPTION_FIELD_NAME + ", " +
                Task.IS_COMPLETED_FIELD_NAME +
                ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }



}
