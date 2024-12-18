package com.example.christopherandersonprojecttwooption2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class loginDBHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                UN_COL + " TEXT," +
                PASS_COL + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addAcct(newUsername : String, newPassword : String ){

        val values = ContentValues()
        values.put(UN_COL, newUsername)
        values.put(PASS_COL, newPassword)

        // create writable variable
        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        // closing database
        db.close()
    }

    // get all data from database
    fun getAcct(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{
        private val DATABASE_NAME = "event tracker logins"

        // database version
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "account_table"

        // below is the variable for account id column
        val ID_COL = "id"

        // below is the variable for username column
        val UN_COL = "username"

        // below is the variable for password column
        val PASS_COL = "password"
    }
    operator fun plus(db2: loginDBHelper): loginDBHelper {
        val cursor = db2.getAcct()
        cursor!!.moveToFirst()
        this.addAcct(cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.UN_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.PASS_COL)))

        while(cursor.moveToNext()){
            this.addAcct(cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.UN_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.PASS_COL)))
            }
        return this
    }
}
