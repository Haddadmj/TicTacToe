package com.mohammad.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var buttonList: List<Button>
    lateinit var board: ArrayList<String>
    lateinit var tvTurn: TextView
    lateinit var tvResult: TextView
    lateinit var translateAnim: Animation
    var playerTurn = "X"
    var turn = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTurn = findViewById(R.id.tvTurn)
        tvResult = findViewById(R.id.tvResult)
        setTurn(playerTurn)
        translateAnim = AnimationUtils.loadAnimation(this, R.anim.slide)


        board = arrayListOf(
            "", "", "",
            "", "", "",
            "", "", ""
        )

        buttonList = listOf(
            findViewById(R.id.bt00),
            findViewById(R.id.bt01),
            findViewById(R.id.bt02),
            findViewById(R.id.bt03),
            findViewById(R.id.bt04),
            findViewById(R.id.bt05),
            findViewById(R.id.bt06),
            findViewById(R.id.bt07),
            findViewById(R.id.bt08),
        )

        buttonList.forEach {
            it.setOnClickListener(this)
        }
    }

    private fun setTurn(playerTurn: String) {
        tvTurn.text = "$playerTurn Turn"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt00 -> setValue(findViewById(R.id.bt00), 0)
            R.id.bt01 -> setValue(findViewById(R.id.bt01), 1)
            R.id.bt02 -> setValue(findViewById(R.id.bt02), 2)
            R.id.bt03 -> setValue(findViewById(R.id.bt03), 3)
            R.id.bt04 -> setValue(findViewById(R.id.bt04), 4)
            R.id.bt05 -> setValue(findViewById(R.id.bt05), 5)
            R.id.bt06 -> setValue(findViewById(R.id.bt06), 6)
            R.id.bt07 -> setValue(findViewById(R.id.bt07), 7)
            R.id.bt08 -> setValue(findViewById(R.id.bt08), 8)
        }
    }

    private fun match(v1: Button, s: String): Boolean {
        return v1.text.toString() == s
    }

    private fun checkWin() {
        // 012 345 678
        if (match(buttonList[0], "X") && match(buttonList[1], "X") && match(buttonList[2], "X")) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[0], "O") && match(buttonList[1], "O") && match(
                buttonList[2],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        } else if (match(buttonList[3], "X") && match(buttonList[4], "X") && match(
                buttonList[5],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[3], "O") && match(buttonList[4], "O") && match(
                buttonList[5],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        } else if (match(buttonList[6], "X") && match(buttonList[7], "X") && match(
                buttonList[8],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[6], "O") && match(buttonList[7], "O") && match(
                buttonList[8],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        }

        // 036 147 258
        else if (match(buttonList[0], "X") && match(buttonList[3], "X") && match(
                buttonList[6],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[0], "O") && match(buttonList[3], "O") && match(
                buttonList[6],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        } else if (match(buttonList[1], "X") && match(buttonList[4], "X") && match(
                buttonList[7],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[1], "O") && match(buttonList[4], "O") && match(
                buttonList[7],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        } else if (match(buttonList[2], "X") && match(buttonList[5], "X") && match(
                buttonList[8],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[2], "O") && match(buttonList[5], "O") && match(
                buttonList[8],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        }

        // 048 246
        else if (match(buttonList[2], "X") && match(buttonList[4], "X") && match(
                buttonList[6],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[2], "O") && match(buttonList[4], "O") && match(
                buttonList[6],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        } else if (match(buttonList[0], "X") && match(buttonList[4], "X") && match(
                buttonList[8],
                "X"
            )
        ) {
            win("Player 1 Win")
            return
        } else if (match(buttonList[0], "O") && match(buttonList[4], "O") && match(
                buttonList[8],
                "O"
            )
        ) {
            win("Player 2 Wins")
            return
        }

        if (turn == 10) {
            win("Draw")
            return
        }

    }

    private fun win(text: String) {
        tvTurn.text = ""
        disableButton()
        tvResult.text = text
        tvResult.startAnimation(translateAnim)

        AlertDialog.Builder(this).setTitle("Want to play Again")
            .setPositiveButton("Yes") { _, _ -> this.recreate() }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }.show()
    }

    private fun disableButton() {
        buttonList.forEach {
            it.isClickable = false
        }
    }

    private fun setValue(v: Button, i: Int) {


        if (playerTurn == "X") {
            v.text = playerTurn
            v.isClickable = false
            board[i] = playerTurn
            playerTurn = "O"
        } else {
            v.text = playerTurn
            v.isClickable = false
            board[i] = playerTurn
            playerTurn = "X"
        }
        setTurn(playerTurn)
        turn++
        if (turn >= 3)
            checkWin()
    }
}