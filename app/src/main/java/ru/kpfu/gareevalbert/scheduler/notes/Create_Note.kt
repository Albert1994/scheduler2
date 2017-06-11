package ru.kpfu.gareevalbert.scheduler.notes

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

import ru.kpfu.gareevalbert.scheduler.R
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals
import ru.kpfu.gareevalbert.scheduler.goal.Goals

class Create_Note : AppCompatActivity() {

    var db_helper_goals: db_Helper_goals = db_Helper_goals(this)
    var title: EditText? = null
    var body:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create__note)

        title = findViewById(R.id.title_note) as EditText
        body = findViewById(R.id.body_note) as EditText

        val btncreate_goal = findViewById(R.id.create_note) as Button
        btncreate_goal.setOnClickListener {
            val title_ = title!!.getText().toString()
            val body_ = body!!.getText().toString()
            val database = db_helper_goals.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(db_Helper_goals.KEY_TITLE, title_)
            contentValues.put(db_Helper_goals.KEY_BODY, body_)
            database.insert(db_Helper_goals.TABLE_NOTES, null, contentValues)
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }
    }
}
