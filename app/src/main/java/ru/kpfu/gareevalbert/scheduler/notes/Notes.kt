package ru.kpfu.gareevalbert.scheduler.notes

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import ru.kpfu.gareevalbert.scheduler.R
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals

class Notes : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mRecyclerAdapter: RecyclerView.Adapter<*>? = null
    private var mlayoutManager: RecyclerView.LayoutManager? = null
    var db_helper_goals: db_Helper_goals = db_Helper_goals(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)


        val myDataset = getDataSet()

        mRecyclerView = findViewById(R.id.notes_recycler) as RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        mlayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setLayoutManager(mlayoutManager)

        mRecyclerAdapter = Adapter_notes(myDataset)
        mRecyclerView!!.setAdapter(mRecyclerAdapter)

        val btngoal = findViewById(R.id.add_notes) as Button
        btngoal.setOnClickListener {
            val intent = Intent(this, Create_Note::class.java)
            startActivity(intent)
        }

    }

    private fun getDataSet(): MutableList<Note> {
        var database:SQLiteDatabase = db_helper_goals.writableDatabase
        var cursor:Cursor = database.query(db_Helper_goals.TABLE_NOTES, null, null, null, null, null, null)
        var mDataSet:MutableList<Note> = mutableListOf()
                if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(db_Helper_goals.KEY_ID)
            val titleIndex = cursor.getColumnIndex(db_Helper_goals.KEY_TITLE)
            val bodyIndex = cursor.getColumnIndex(db_Helper_goals.KEY_BODY)
            for (i in 0..cursor.count - 1) {
                val goal = Note()
                goal.id = cursor.getInt(idIndex)
                goal.title = cursor.getString(titleIndex)
                goal.body = cursor.getString(bodyIndex)
                mDataSet.add(goal)
                cursor.moveToNext()
            }
        } else
            Log.d("error", "error")

        cursor.close()
        db_helper_goals.close()
        return mDataSet
    }
}
