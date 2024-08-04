package com.example.worldskillssnewapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NavHeader : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_header)

        val textUser = findViewById<TextView>(R.id.textViewUser)

        val username = intent.getStringExtra("username")

        textUser.text = "$username"
    }
}
