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

        // 코루틴은 job을 반환하며, 이 job은 특정 변수에 저장할 수 있다.
        val job = GlobalScope.launch(Dispatchers.Default) {
            // repeat 함수 : 말 그대로 블록 내부에 있는 동작들을 repeat
//            repeat(5) {
//                Log.d(TAG, "Coroutine is still working!!")
//                delay(1000L)
//            }

            Log.d(TAG, "Starting long running calculation")

            withTimeout(3000L) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.d(TAG, "Result for i=$i : ${fib(i)}")
                    }

                }
            }

            Log.d(TAG, "Ending long running calculation")
        }

        // 할당된 job에 대하여, 그 job이 작업을 마칠 때까지 기다릴 수 있음.
        // 이러한 역할을 해주는 것이 바로 join() 함수이다.
        // join 함수는 suspend 함수이므로, Main Thread에서는 수행할 수 없다.
//        runBlocking {
//            // join 함수가 해주는 일
//            // job이 속한 Thread의 수행이 완료될 때까지
//            // 현재 코루틴의 thread를 block한다.
//            job.join()
//            // job이 속한 코루틴의 수행이 마치면, 이어서 수행됨.
//            Log.d(TAG, "Main Thread is Continuing!")
//        }

//        runBlocking {
//            delay(2000L)
//            job.cancel()
//            Log.d(TAG, "Job canceled")
//        }


    }

    // 오랜 시간이 걸리는 작업이라고 가정. - cancel이 동작하는 방법을 보여주기 위함
    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}
