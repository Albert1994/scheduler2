package ru.kpfu.gareevalbert.scheduler.goal;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ru.kpfu.gareevalbert.scheduler.R;
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals;

public class Adapter_goals extends RecyclerView.Adapter<Adapter_goals.ViewHolder> {

    private ArrayList<Goal> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        // наш пункт состоит только из одного TextView
        public TextView TextTitle;
        public TextView TextBody;

        public  Button mDelete;
        private Context context;


        public ViewHolder(View v, Context context_) {
            super(v);
            context = context_;
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
        ViewHolder vh = new ViewHolder(v,parent.getContext());
        return vh;
    }

    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.TextTitle.setText(mDataset.get(position).title);
        holder.TextBody.setText(mDataset.get(position).body);
        holder.mDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db_Helper_goals db_helper_goals = new db_Helper_goals(holder.context);
                SQLiteDatabase database = db_helper_goals.getWritableDatabase();
                int _id = mDataset.get(position).id;
                Log.d("ad", String.valueOf(_id));
                database.delete(db_Helper_goals.TABEL_GOALS,"_id = " + _id,null);
                Intent intent = new Intent(holder.context, Goals.class);
                holder.context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context,Goal_one.class);
                intent.putExtra("ID",String.valueOf(mDataset.get(position).id));
                holder.context.startActivity(intent);
            }});
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