package ru.kpfu.gareevalbert.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

public class Goals extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        String[] myDataset = getDataSet();

        mRecyclerView = (RecyclerView) findViewById(R.id.goals_recycler);
        mRecyclerView.setHasFixedSize(true);

        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);

        mRecyclerAdapter = new Adapter_goals(myDataset);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }


    private String[] getDataSet(){
        String[] mDataSet = new String[100];
        for (int i = 0; i < 100; i++) {
            mDataSet[i] = "item" + i;
        }
        return mDataSet;
    }
}
