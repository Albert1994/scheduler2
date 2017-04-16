package ru.kpfu.gareevalbert.scheduler;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

public class Goals extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    db_Helper_goals db_helper_goals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        final String[] myDataset = getDataSet();
        Button btngoal = (Button) findViewById(R.id.add_goal);


        mRecyclerView = (RecyclerView) findViewById(R.id.goals_recycler);
        mRecyclerView.setHasFixedSize(true);

        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);

        mRecyclerAdapter = new Adapter_goals(myDataset);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        db_helper_goals = new db_Helper_goals(this);

        btngoal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String a = "sad";
                myDataset[myDataset.length-1] = a;
              /*  SQLiteDatabase database = db_helper_goals.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(db_helper_goals.KEY_NAME, "a23");
                database.insert(db_helper_goals.TABLE_NAME, null, contentValues);*/
            }});

    }


    private String[] getDataSet(){
        String[] mDataSet = new String[3];
        for (int i = 0; i < 3; i++) {
            mDataSet[i] = "item" + i;
        }
        return mDataSet;
    }
}
