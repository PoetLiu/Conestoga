package com.peng.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val courses = listOf(
            "Web Traffic and Search Engine",
            "Advanced Full-Stack Programming",
            "Programming with .NET",
            "Advanced Mobile Application Dev",
            "Programming with MySQL")

        printCourse("Peng", courses)
    }

    private fun printCourse(name: String, courses: List<String>) {
        Log.i("MAIN", "Hello, $name! You're taking following courses: ")
        for (course in courses) {
            Log.i("MAIN", course)
        }
    }
}