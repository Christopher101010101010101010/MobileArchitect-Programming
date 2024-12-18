package com.example.christopherandersonprojecttwooption2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.christopherandersonprojecttwooption2.com.example.christopherandersonprojecttwooption2.eventDBHelper

class UpdateApptActivity : ComponentActivity() {

    companion object{

         var dbAppts: eventDBHelper = eventDBHelper( MainActivity2.contextApptsScreen , null)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_appointments_window)

        var updateApptTitle = findViewById(R.id.editTextUpdateTile) as EditText
        var updateApptDateTime = findViewById(R.id.editTextUpdateDateTime) as EditText
        var updateApptDescription = findViewById(R.id.editTextUpdateDescription) as EditText

        var switchAdd = findViewById(R.id.switchAdd) as Switch
        var switchRemove = findViewById(R.id.switchRemove) as Switch
        var buttonSubmit = findViewById(R.id.buttonUpdate) as Button
        var buttonExitUpdate = findViewById(R.id.buttonExitUpdate) as Button
        var swAdd : Boolean = false
        var swRemove : Boolean = false

        switchAdd.setOnClickListener{
            if(switchAdd.isChecked()){
                swAdd = true
            }else{
                swAdd = false
            }
        }
        switchRemove.setOnClickListener{
            if(switchRemove.isChecked()){
                swRemove = true
            }else{
                swRemove = false
            }
        }

        buttonSubmit.setOnClickListener{
            if((!swAdd && swRemove) || (swAdd && !swRemove)){
                val textUpdateApptTitle = updateApptTitle.text.toString()
                val textUpdateApptDtTm = updateApptDateTime.text.toString()
                val textUpdateApptDescription = updateApptDescription.text.toString()

                // spawn instance of global database object shared by MainActivity2 and add/remove object from database
                if(swAdd){
                    dbAppts.addAppt(textUpdateApptTitle, textUpdateApptDtTm, textUpdateApptDescription)

                    //call SMSMessage Activity to confirm/deny SMS Message Send permission
                    val intentSMSPermission: Intent = Intent(this@UpdateApptActivity, SMSMessage::class.java)

                    //intentSMSPermission.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intentSMSPermission.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intentSMSPermission.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    intentSMSPermission.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intentSMSPermission)

                    Toast.makeText(this, "new event added", Toast.LENGTH_SHORT)

                }else if(swRemove) {
                    if (textUpdateApptTitle == "" || textUpdateApptTitle == "enter event title") {
                        Toast.makeText(
                            MainActivity2.contextApptsScreen,
                            "Please enter title of the event for removal.",
                            Toast.LENGTH_SHORT
                        )
                    } else {
                        dbAppts.removeAppt(textUpdateApptTitle)

                    }
                }
            }
            else{
                //do nothing if both switches are active or not active
            }
        }

        buttonExitUpdate.setOnClickListener{
            //cancel update appt window and return to MainActivity2 window
            finish()
        }



    }
}