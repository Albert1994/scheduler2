package ru.kpfu.gareevalbert.scheduler;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

public class Goals extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    db_Helper_goals db_helper_goals;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        db_helper_goals = new db_Helper_goals(this);
        final ArrayList<Goal> myDataset = getDataSet();



        mRecyclerView = (RecyclerView) findViewById(R.id.goals_recycler);
        mRecyclerView.setHasFixedSize(true);

        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);

        mRecyclerAdapter = new Adapter_goals(myDataset);
        mRecyclerView.setAdapter(mRecyclerAdapter);



        Button btngoal = (Button) findViewById(R.id.add_goal);
        btngoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Goals.this, Create_Goal.class);
                    startActivity(intent);
            }
        });
    }
    public void deleteItem(int position){
        database = db_helper_goals.getWritableDatabase();
        position++;
        database.delete(db_Helper_goals.TABEL_GOALS, db_Helper_goals.KEY_ID + "=" + position, null);
        db_helper_goals.close();
    }

    private ArrayList<Goal> getDataSet() {
        database = db_helper_goals.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = database.query(db_Helper_goals.TABEL_GOALS,null,null,null,null,null,null);
        ArrayList<Goal> mDataSet = new ArrayList<Goal>();
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(db_Helper_goals.KEY_ID);
            int titleIndex = cursor.getColumnIndex(db_Helper_goals.KEY_TITLE);
            int bodyIndex = cursor.getColumnIndex(db_Helper_goals.KEY_BODY);
                for(int i = 0; i < cursor.getCount(); i++){
                    Goal goal = new Goal();
                    goal.id = cursor.getInt(idIndex);
                    goal.title = cursor.getString(titleIndex);
                    goal.body = cursor.getString(bodyIndex);
                    mDataSet.add(goal);
                    cursor.moveToNext();
                }
        }else Log.d("error","error");

        cursor.close();
        db_helper_goals.close();
        return mDataSet;
    }
}

