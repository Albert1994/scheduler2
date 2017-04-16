package ru.kpfu.gareevalbert.scheduler

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.view.View.OnClickListener
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnnotes = findViewById(R.id.button8)
        val btnobjectives = findViewById(R.id.button10)
        val btnicome_expenses = findViewById(R.id.button11)

        btnnotes.setOnClickListener { view ->
            val myIntent = Intent(view.context, Notes::class.java)
            startActivity(myIntent)
            finish()
        }
        btnobjectives.setOnClickListener { view ->
            val myIntent = Intent(view.context, Goals::class.java)
            startActivity(myIntent)
            finish()
        }
        btnicome_expenses.setOnClickListener { view ->
            val myIntent = Intent(view.context, Expenses_incomes::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}
