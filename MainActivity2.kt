package com.example.christopherandersonprojecttwooption2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.GridView

import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.christopherandersonprojecttwooption2.com.example.christopherandersonprojecttwooption2.eventDBHelper

data class GridViewEvents(val eventTitle: String, val dateTime: Int, val Description: String)

class MainActivity2 : ComponentActivity() {

    init{
        contextApptsScreen = this@MainActivity2
    }

    companion object{
        lateinit var contextApptsScreen: Context
    }
    lateinit var eventGV: GridView
    lateinit var eventDB: List<GridViewModule>

    var apptDB = eventDBHelper(this, null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        eventGV = findViewById(R.id.main2Gridview)
        eventDB = ArrayList<GridViewModule>()

        val cursor = apptDB.getAppt()

        //update gridview events list with updated appointments
        cursor!!.moveToFirst()
        eventDB = eventDB + GridViewModule(cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.TITLE_COL)),
            cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DATETIME_COL)),
            cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DESCRIPTION_COL)))

        while(cursor.moveToNext()){
            eventDB = eventDB + GridViewModule(cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.TITLE_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DATETIME_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(eventDBHelper.DESCRIPTION_COL)))
        }


        //test population
        eventDB = eventDB + GridViewModule("test1 Doctor Appointment", "10/03/2025, 8:00am", "Annual check up")
        eventDB = eventDB + GridViewModule("test2 Daughter's Play", "10/14/2025, 3:00pm", "Auditorum C, Seat 42")
        eventDB = eventDB + GridViewModule("test3 Husband's Birthday Party", "11/23/2025, 11:00am", "Invite Ronny and Fred. Remember to pickup chocolate fudge" +
                                           " double layered cake from Wilma's House of Sweets!")

        val apptAdapter = GridViewAdapter(eventDB, this@MainActivity2)
        eventGV.adapter = apptAdapter

        val buttonUpdateAppt = findViewById(R.id.idButtonUpdateEvent) as Button
        buttonUpdateAppt.setOnClickListener{
            val intentUpdateApptAct: Intent = Intent(this@MainActivity2, UpdateApptActivity::class.java)

            //intentUpdateApptAct.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intentUpdateApptAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intentUpdateApptAct.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            intentUpdateApptAct.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intentUpdateApptAct)
            apptDB += UpdateApptActivity.dbAppts
        }

        //select and view GridView item..?

    }

    //function to check Date/Time of events for sms messaging schedule
    fun checkApptDateTime(){
        //-check new event object for sms message send permission
        //-schedule sms messaging to send message notification with respect to
        //      event's date/time member

    }
}

