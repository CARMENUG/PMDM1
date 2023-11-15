package com.example.evaluable1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class PiedraPapelTijeras : AppCompatActivity() {

    private lateinit var imgJugador : ImageView
    private lateinit var  imgCPU : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piedra_papel_tijeras)

        imgJugador = findViewById(R.id.imgjugador)
        imgCPU = findViewById(R.id.imgCPU)

        val btnpiedra = findViewById<Button>(R.id.btnpiedra)
        val btnpapel = findViewById<Button>(R.id.btnpapel)
        val btntijeras = findViewById<Button>(R.id.btntijera)
        val btnsalir = findViewById<Button>(R.id.btnsalir)

        btnpiedra.setOnClickListener {
            imgJugador.setImageResource(R.drawable.piedra)
            jugar("piedra")
        }
        btnpapel.setOnClickListener {
            imgJugador.setImageResource(R.drawable.papel)
            jugar("papel")
        }
        btntijeras.setOnClickListener {
            imgJugador.setImageResource(R.drawable.tijeras)
            jugar("tijeras")
        }
        btnsalir.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
    private fun jugar(eleccionUsuario: String) {
        val opciones = arrayOf("piedra", "papel", "tijeras")
        val random = Random
        val eleccionComputadora = opciones[random.nextInt(opciones.size)]

        when (eleccionComputadora) {
            "piedra" -> imgCPU.setImageResource(R.drawable.piedra)
            "papel" -> imgCPU.setImageResource(R.drawable.papel)
            "tijeras" -> imgCPU.setImageResource(R.drawable.tijeras)
        }

        val resultado = determinarResultado(eleccionUsuario, eleccionComputadora); mostrarResultados(resultado)
    }
    private fun determinarResultado(eleccionUsuario: String, eleccionComputadora: String): String {
        return when {
            eleccionUsuario == eleccionComputadora ->"Empate"
            (eleccionUsuario == "piedra" && eleccionComputadora == "tijeras") ||
                    (eleccionUsuario == "papel" && eleccionComputadora == "piedra") ||
                    (eleccionUsuario == "tijeras" && eleccionComputadora == "papel") -> "Ganaste"
            else -> "Computadora gana"

        }
    }
    private fun mostrarResultados(resultado: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Resultado: $resultado")
        builder.setPositiveButton("Cerrar") { _, _ -> }
        val dialog = builder.create()
        dialog.show()
    }
}
