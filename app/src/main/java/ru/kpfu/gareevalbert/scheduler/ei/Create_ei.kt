package ru.kpfu.gareevalbert.scheduler.ei

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import ru.kpfu.gareevalbert.scheduler.R
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener


class Create_ei : AppCompatActivity() {
    var db_helper_goals: db_Helper_goals = db_Helper_goals(this)
    var catygory: Spinner? = null
    var money: EditText? = null
    var title: EditText? = null
    var catygory_name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ei)

        val spinner = findViewById(R.id.category_spinner) as Spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                catygory_name = adapter.getItem(position).toString()
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {}
        }
        money = findViewById(R.id.money_ei) as EditText
        title = findViewById(R.id.title_ei) as EditText


        val btncreate_goal = findViewById(R.id.create_ei) as Button
        btncreate_goal.setOnClickListener {
            val money_ = money!!.getText().toString()
            val title_ = title!!.getText().toString()
            val database = db_helper_goals.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(db_Helper_goals.KEY_EI_CATEGORY, catygory_name)
            contentValues.put(db_Helper_goals.KEY_EI_MONEY, money_)
            contentValues.put(db_Helper_goals.KEY_TITLE,title_)
            database.insert(db_Helper_goals.TABLE_EI, null, contentValues)
            val intent = Intent(this, Expenses_incomes::class.java)
            startActivity(intent)
        }


    }
}
