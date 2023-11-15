package com.example.evaluable1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnjuego2 = findViewById<Button>(R.id.btnjuego2)
        btnjuego2.setOnClickListener { navigateTojuego2() }

        val btnjuego1 = findViewById<Button>(R.id.btnjuego1)
        btnjuego1.setOnClickListener { navigateTojuego1() }

    }

    private fun navigateTojuego2(){
        val intent = Intent(this, PiedraPapelTijeras::class.java)
        startActivity(intent)
    }

    private fun navigateTojuego1(){
        val intent = Intent(this, Raya3::class.java)
        startActivity(intent)
    }

}