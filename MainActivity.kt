package com.example.christopherandersonprojecttwooption2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity


/*Log-In Window Activity */



class MainActivity : ComponentActivity() {
    init{
        contextLogin = this@MainActivity
    }

    companion object{
        lateinit var contextLogin: Context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var dbptr = loginDBHelper(this, null)

        /* set click listeners for new account button and log-in button */
        val buttonNewAcc : Button = findViewById(R.id.buttonNewUser) as Button
        buttonNewAcc.setOnClickListener{
            //activate new account window and transfer new account into account database
            val intentNewAcc: Intent = Intent(this@MainActivity, newAccountWindow::class.java)

            //intentNewAcc.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            //intentNewAcc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intentNewAcc.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intentNewAcc.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            intentNewAcc.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intentNewAcc)
            dbptr += newAccountWindow.dbptrNAW
        }

        val buttonLogIn = findViewById(R.id.buttonLogin) as Button
        buttonLogIn.setOnClickListener{
            //check credentials against database and transfer authorized user to the event tracker screen



            val editTxtUsrnm1 = findViewById(R.id.idUsername) as EditText
            val editTxtPass1 = findViewById(R.id.idPassword) as EditText

            val aUsername = editTxtUsrnm1.text.toString()
            val aPassword = editTxtPass1.text.toString()

            val cursor = dbptr.getAcct()
            var isFound: Boolean = false

            cursor!!.moveToFirst()
            if(cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.UN_COL)) == aUsername &&
                cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.PASS_COL)) == aPassword){
                isFound = true
            }

            while(cursor.moveToNext() && !isFound){
                if(cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.UN_COL)) == aUsername &&
                    cursor.getString(cursor.getColumnIndexOrThrow(loginDBHelper.PASS_COL)) == aPassword){
                    isFound = true
                    break
                }
            }

            if(isFound){
                val intentEvntWdw: Intent = Intent(this@MainActivity, MainActivity2::class.java)

                //intentEvntWdw.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                intentEvntWdw.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intentEvntWdw.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //intentEvntWdw.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                intentEvntWdw.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intentEvntWdw)
            }

            cursor.close()
        }


    }




}

