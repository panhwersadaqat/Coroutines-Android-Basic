package com.example.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var counterButton: Button
    private lateinit var taskButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.tv)
        counterButton = findViewById(R.id.button)
        taskButton = findViewById(R.id.button_longtask)

        counterButton.setOnClickListener {
            counterUpdate()
        }

        taskButton.setOnClickListener {
            // first way to launch Coroutines
            CoroutineScope(Dispatchers.IO).launch {
                executeLongTask()
            }

            //  way to launch coroutines
            // this scope is bind with application

            /*GlobalScope.launch(Dispatchers.Main) {
                //code here
            }*/

            //  way to launch coroutines
            // this scope is bind with main activity

            /*MainScope().launch(Dispatchers.Default) {
                //code here
            }*/


            //testing suspended function with coroutines
            CoroutineScope(Dispatchers.Main).launch {
                task1()
            }

            CoroutineScope(Dispatchers.Main).launch {
                task2()
            }

        }


    }

    private fun counterUpdate() {
        counterTextView.text = "${counterTextView.text.toString().toInt()+1}"
    }

    private fun executeLongTask() {
        for(i in 1..1000000000L) {

        }
    }

    suspend fun task1() {
        Log.d("TAG", "Starting task 1")
        // yield is called for delay/suspend coroutine thread
        yield()
        //Another way to suspend function with time choice
        //delay(1000)
        Log.d("TAG", "Ending task 1")
    }

    suspend fun task2() {
        Log.d("TAG", "Starting task 2")
        // yield is called for delay/suspend coroutine thread
        yield()
        //Another way to suspend function with time choice
        //delay(1000)
        Log.d("TAG","Ending task 2")
    }
}