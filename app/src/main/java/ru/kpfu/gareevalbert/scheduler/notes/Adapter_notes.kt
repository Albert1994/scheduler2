package ru.kpfu.gareevalbert.scheduler.notes


import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import java.util.ArrayList

import ru.kpfu.gareevalbert.scheduler.R

class Adapter_notes(var mDataset:MutableList<Note>) : RecyclerView.Adapter<Adapter_notes.ViewHolder_notes>() {



    class ViewHolder_notes(v: View) : RecyclerView.ViewHolder(v) {
        // наш пункт состоит только из одного TextView
        var TextTitle: TextView
        var TextBody: TextView
        var mDelete: Button


        init {
            TextTitle = v.findViewById(R.id.tv_recycler_item) as TextView
            TextBody = v.findViewById(R.id.tv_recycler_item2) as TextView
            mDelete = v.findViewById(R.id.delete_goal) as Button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_notes.ViewHolder_notes {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.goals_item, parent, false)
        val vh = ViewHolder_notes(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder_notes, position: Int) {
        holder.TextTitle.setText(mDataset.get(position).title)
        holder.TextBody.setText(mDataset.get(position).body)
    }

    override fun getItemCount(): Int {
        return mDataset.count()
    }
}

class Note {
    var id: Int = 0
    var title: String? = null
    var body: String? = null
}