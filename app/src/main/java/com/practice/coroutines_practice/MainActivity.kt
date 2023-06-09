package com.practice.coroutines_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            // IO를 위한 Thread에서 시작
            Log.d(TAG, "Starting Coroutine in thread ${Thread.currentThread().name}")
            val networkResult = networkCall()

            // 그런데, 이제 이 결과를 UI에 그리고 싶음.
            // UI에 그리는 것은 Main Thread에서만 가능
            // 따라서, Context Switching이 필요 => Main
            // 이 때 사용하는 함수가 바로 withContext 함수이다.
            // 현재 Coroutine이 실행될 Context를 바꾸겠다는 의미
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Starting UI in thread ${Thread.currentThread().name}")
                // 여기서부터, Main Thread에서 실행될 것임을 의미
                findViewById<TextView>(R.id.tv_hello).text = networkResult
            }

        }

    }
    private suspend fun networkCall(): String {
        Log.d(TAG, "network call started")
        delay(1000L) // 이 시간동안 네트워크 통신이 일어난다고 가정
        Log.d(TAG, "network call ended")
        return "network result"
    }

//    private suspend fun databaseQuery(str: String): String {
//        Log.d(TAG, "db call started")
//        delay(1000L) // 이 시간동안 db 통신이 일어난다고 가정
//        Log.d(TAG, "db call ended")
//        return str+"database process"
//    }
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
