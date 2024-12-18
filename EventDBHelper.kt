package com.example.christopherandersonprojecttwooption2.com.example.christopherandersonprojecttwooption2


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.christopherandersonprojecttwooption2.MainActivity2
import com.example.christopherandersonprojecttwooption2.loginDBHelper

class eventDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + TITLE_COL + " TEXT, " +
                DATETIME_COL + " TEXT," +
                DESCRIPTION_COL + " TEXT" + ")")

        // call sqlite for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // check if table exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addAppt(newTitle: String, newDateTime: String, newDescript: String) {

        // create a content values variable
        val values = ContentValues()

        // insert values of key-value pair
        values.put(TITLE_COL, newTitle)
        values.put(DATETIME_COL, newDateTime)
        values.put(DESCRIPTION_COL, newDescript)

        // creating a writable variable
        val db = this.writableDatabase

        // all values are inserted
        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun removeAppt(newTitle: String) {
        val db = this.writableDatabase

        val whereClause = "TITLE_COL = ?"
        val whereArgs = arrayOf(newTitle)

        db.delete(TABLE_NAME, whereClause, whereArgs)

        db.close()
    }

    fun getAppt(): Cursor? {

        // here we are creating a readable
        // variable of our database
        val db = this.readableDatabase

        // below code returns cursor
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object {
        // below is variable for database name
        private val DATABASE_NAME = "Events Database"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "appt_table"

        // below is the variable for title column
        val TITLE_COL = "title"

        // below is the variable for datetime column
        val DATETIME_COL = "datetime"

        // below is the variable for description column
        val DESCRIPTION_COL = "description"
    }

    operator fun plus(db2: eventDBHelper): eventDBHelper {
        val cursor = db2.getAppt()
        cursor!!.moveToFirst()
        this.addAppt(cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.TITLE_COL)),
            cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DATETIME_COL)),
            cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DESCRIPTION_COL)))

        while(cursor.moveToNext()){
            this.addAppt(cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.TITLE_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DATETIME_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DESCRIPTION_COL)))
        }
        return this
    }
}