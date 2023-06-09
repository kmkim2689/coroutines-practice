package com.practice.coroutines_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val networkResult = networkCall()
            Log.d("result", networkResult)
            val dbResult = databaseQuery(networkResult)
            Log.d("result", dbResult)
        }

    }
    private suspend fun networkCall(): String {
        Log.d(TAG, "network call started")
        delay(1000L) // 이 시간동안 네트워크 통신이 일어난다고 가정
        Log.d(TAG, "network call ended")
        return "network result"
    }

    private suspend fun databaseQuery(str: String): String {
        Log.d(TAG, "db call started")
        delay(1000L) // 이 시간동안 db 통신이 일어난다고 가정
        Log.d(TAG, "db call ended")
        return str+"database process"
    }
}

// The simplest way to start coroutine : GlobalScope
// Not really the best way...
//GlobalScope.launch {
//    delay(3000L)
//    Log.d(TAG, "Coroutine says Hello from thread ${Thread.currentThread().name}")
//}
//
//// 위의 코루틴과 다른 Thread에서 수행됨
//Log.d(TAG, "Hello from the thread ${Thread.currentThread().name}")
//
