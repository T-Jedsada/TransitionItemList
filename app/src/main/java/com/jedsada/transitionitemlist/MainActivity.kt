package com.jedsada.transitionitemlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userList = getJsonToMock("movies.json", MovieDao::class.java)
        Log.d("POND", "result $userList")
    }

    private fun <T> getJsonToMock(fileName: String, className: Class<T>): T =
            GsonBuilder().create().fromJson<T>(getJsonFromResources(fileName), className)

    private fun getJsonFromResources(fileName: String): String? =
            this.javaClass.classLoader.getResourceAsStream(fileName)?.bufferedReader().use { it?.readText() }
}
