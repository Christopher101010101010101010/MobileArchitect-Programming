package com.example.christopherandersonprojecttwooption2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.ComponentActivity

class SMSMessage :  ComponentActivity(){

    companion object{
        var SMSPermission: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sms_notification)

        val buttonClose = findViewById(R.id.buttonExit1) as Button
        val buttonSubmit = findViewById(R.id.buttonSumbitPermission) as Button
        val checkBoxPerm = findViewById(R.id.checkBoxPermission) as CheckBox

        buttonSubmit.setOnClickListener{
            if(checkBoxPerm.isChecked()){
                //allow send sms permission
                SMSPermission = true
            }else{
                //deny sms permission
                SMSPermission = false
            }
        }

        buttonClose.setOnClickListener{
            finish()
        }


    }
}