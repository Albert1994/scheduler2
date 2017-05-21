package ru.kpfu.gareevalbert.scheduler;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter_goals extends RecyclerView.Adapter<Adapter_goals.ViewHolder> {

    private ArrayList<Goal> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        // наш пункт состоит только из одного TextView
        public TextView TextTitle;
        public TextView TextBody;
        public  Button mDelete;


        public ViewHolder(View v) {
            super(v);
            TextTitle = (TextView) v.findViewById(R.id.tv_recycler_item);
            TextBody = (TextView) v.findViewById(R.id.tv_recycler_item2);
            mDelete = (Button) v.findViewById(R.id.delete_goal);
        }
    }

    public Adapter_goals(ArrayList<Goal> dataset){
        mDataset = dataset;
    }

    public Adapter_goals.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.goals_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.TextTitle.setText(mDataset.get(position).title);
        holder.TextBody.setText(mDataset.get(position).body);

        holder.mDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("dsad", "" + position);
                new Goals().deleteItem(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    public int getItemCount(){
        return mDataset.size();
    }

}

class Goal{
    int id;
    String title;
    String body;
}