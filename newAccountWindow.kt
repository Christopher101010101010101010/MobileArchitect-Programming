package com.example.christopherandersonprojecttwooption2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class newAccountWindow : ComponentActivity(){

    companion object {

        var dbptrNAW = loginDBHelper(MainActivity.contextLogin, null)
    }

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_account_window)

        val buttonNewAccCreate = findViewById(R.id.buttonGenerateAcct) as Button
        buttonNewAccCreate.setOnClickListener{


            val editTxtUsrnm2 = findViewById(R.id.editTextUsername2) as EditText
            val editTxtPass2 = findViewById(R.id.editTextPassword2) as EditText

            val aUsername = editTxtUsrnm2.text.toString()
            val aPassword = editTxtPass2.text.toString()

            dbptrNAW.addAcct(aUsername, aPassword)

            editTxtUsrnm2.text.clear()
            editTxtPass2.text.clear()

            Toast.makeText(this, "New Account created", Toast.LENGTH_SHORT).show()
        }

        val buttonClose = findViewById(R.id.buttonExit1) as Button
        buttonClose.setOnClickListener{
            finish()
        }

        fun getData(): loginDBHelper{
                return dbptrNAW
        }

    }


}