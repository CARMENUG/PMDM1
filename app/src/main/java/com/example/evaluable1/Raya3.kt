package com.example.evaluable1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class Raya3 : AppCompatActivity() {


    private lateinit var textView: TextView
    private var currentPlayer = "X"
    private var winner: String? = null
    private var moves = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raya3)

        textView = findViewById(R.id.textView)



        //inicializa los botonoes
        val b1 = findViewById<Button>(R.id.b1)
        val b2 = findViewById<Button>(R.id.b2)
        val b3 = findViewById<Button>(R.id.b3)
        val b4 = findViewById<Button>(R.id.b4)
        val b5 = findViewById<Button>(R.id.b5)
        val b6 = findViewById<Button>(R.id.b6)
        val b7 = findViewById<Button>(R.id.b7)
        val b8 = findViewById<Button>(R.id.b8)
        val b9 = findViewById<Button>(R.id.b9)

        //establece los listener para cada boton
        b1.setOnClickListener { onButtonClick(b1) }
        b2.setOnClickListener { onButtonClick(b2) }
        b3.setOnClickListener { onButtonClick(b3) }
        b4.setOnClickListener { onButtonClick(b4) }
        b5.setOnClickListener { onButtonClick(b5) }
        b6.setOnClickListener { onButtonClick(b6) }
        b7.setOnClickListener { onButtonClick(b7) }
        b8.setOnClickListener { onButtonClick(b8) }
        b9.setOnClickListener { onButtonClick(b9) }


        //establece el listener para el boton de reinicio
        val resetButton = findViewById<Button>(R.id.btnreiniciar)
        resetButton.setOnClickListener { resetGame() }

        //establece el listener para el boton de salida al menu principal
        val exitButton = findViewById<Button>(R.id.btnsalida)
        exitButton.setOnClickListener { finish() }
    }

    private fun onButtonClick(button: Button) {
        Log.d("Juego", "onButtonClick called")
        if (winner == null || winner == "false")
            if (button.text == " " ) {
                currentPlayer = "X"
                button.text = currentPlayer
                moves++
                winner = checkWin()

                if (winner == "false" && moves < 9) {
                    currentPlayer = "O"
                    turnoComputadora()
                    moves++
                    winner = checkWin()
                }
            }
            if (winner == "true") {
                textView.text = "Ganador: $currentPlayer"
            } else if (moves == 9) {
                Log.d("Juego", "2 ")
                textView.text = "Empate"
            }

    }




    private fun turnoComputadora() {
        val availableButtons = listOf(
            findViewById<Button>(R.id.b1), findViewById<Button>(R.id.b2), findViewById<Button>(R.id.b3),
            findViewById<Button>(R.id.b4), findViewById<Button>(R.id.b5), findViewById<Button>(R.id.b6),
            findViewById<Button>(R.id.b7), findViewById<Button>(R.id.b8), findViewById<Button>(R.id.b9)
        ).filter { it.text == " " }

        if (availableButtons.isNotEmpty()) {
            val randomButton = availableButtons.random()
            randomButton.text = "O"
        }
    }
    private fun checkWin(): String? {
        val board = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = resources.getIdentifier("b${i * 3 + j + 1}", "id", packageName)
                val button = findViewById<Button>(buttonId)
                board[i][j] = button.text.toString()
            }
        }
        // Comprobamos las filas, columnas y diagonales
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return "true"
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return "true"
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return "true"
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return "true"

        return "false"
    }


    private fun resetGame(){
        val buttonIds = listOf(
            R.id.b1, R.id.b2, R.id.b3,
            R.id.b4, R.id.b5, R.id.b6,
            R.id.b7, R.id.b8, R.id.b9
        )
        for(buttonId in buttonIds){
            val button = findViewById<Button>(buttonId)
            button.text = " "
        }
        currentPlayer = "X"
        winner = null
        moves = 0
        textView.text = "Juega..."
    }

}