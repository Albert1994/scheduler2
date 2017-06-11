package ru.kpfu.gareevalbert.scheduler.ei

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import ru.kpfu.gareevalbert.scheduler.R
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals

class Expenses_incomes internal constructor() : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mRecyclerAdapter: RecyclerView.Adapter<*>? = null
    private var mlayoutManager: RecyclerView.LayoutManager? = null
    var db_helper_goals: db_Helper_goals = db_Helper_goals(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses_incomes)

        val myDataset = getDataSet()

        mRecyclerView = findViewById(R.id.ei_recycler) as RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        mlayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setLayoutManager(mlayoutManager)

        mRecyclerAdapter = Adapter_ei(myDataset)
        mRecyclerView!!.setAdapter(mRecyclerAdapter)

        val btngoal = findViewById(R.id.add_ei) as Button
        btngoal.setOnClickListener {
            val intent = Intent(this, Create_ei::class.java)
            startActivity(intent)
        }
    }

    private fun getDataSet(): MutableList<EI> {
        var database:SQLiteDatabase = db_helper_goals.writableDatabase
        var cursor: Cursor = database.query(db_Helper_goals.TABLE_EI, null, null, null, null, null, null)
        var mDataSet:MutableList<EI> = mutableListOf()
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(db_Helper_goals.KEY_ID)
            val categoryIndex = cursor.getColumnIndex(db_Helper_goals.KEY_EI_CATEGORY)
            val titleIndex = cursor.getColumnIndex(db_Helper_goals.KEY_TITLE)
            val bodyIndex = cursor.getColumnIndex(db_Helper_goals.KEY_EI_MONEY)
            for (i in 0..cursor.count - 1) {
                val ei = EI()
                ei.id = cursor.getInt(idIndex)
                ei.category = cursor.getString(categoryIndex)
                ei.title = cursor.getString(titleIndex)
                ei.money = cursor.getInt(bodyIndex)
                mDataSet.add(ei)
                cursor.moveToNext()
            }
        } else
            Log.d("error", "error")

        cursor.close()
        db_helper_goals.close()
        return mDataSet
    }
}
