package ru.kpfu.gareevalbert.scheduler

import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.app.Activity

class startActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        Handler().postDelayed({
            val i = Intent(this@startActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, (3 * 1000).toLong())

    }
}

