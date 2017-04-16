package ru.kpfu.gareevalbert.scheduler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter_goals extends RecyclerView.Adapter<Adapter_goals.ViewHolder> {

    private String[] mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
        }
    }

    public Adapter_goals(String[] dataset){
        mDataset = dataset;
    }

    public Adapter_goals.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.goals_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mTextView.setText(mDataset[position]);
    }

    public int getItemCount(){
        return mDataset.length;
    }

}
