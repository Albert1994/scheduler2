package ru.kpfu.gareevalbert.scheduler.ei

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ru.kpfu.gareevalbert.scheduler.R

class Adapter_ei(var mDataset:MutableList<EI>) : RecyclerView.Adapter<Adapter_ei.ViewHolder_ei>() {

    class ViewHolder_ei(v: View) : RecyclerView.ViewHolder(v) {
        // наш пункт состоит только из одного TextView
        var TextCategory: TextView
        var TextMoney: TextView
        var TextTitle: TextView
        var mDelete: Button


        init {
            TextCategory = v.findViewById(R.id.ei_category) as TextView
            TextMoney = v.findViewById(R.id.ei_money) as TextView
            TextTitle = v.findViewById(R.id.ei_title) as TextView
            mDelete = v.findViewById(R.id.delete_ei) as Button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_ei.ViewHolder_ei {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ie_item, parent, false)
        val vh = ViewHolder_ei(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder_ei, position: Int) {
        holder.TextCategory.setText(mDataset.get(position).category)
        holder.TextTitle.setText(mDataset.get(position).title)
        holder.TextMoney.setText(mDataset.get(position).money.toString())
    }

    override fun getItemCount(): Int {
        return mDataset.count()
    }
}

class EI {
    var id: Int = 0
    var category: String? = null
    var title: String? = null
    var money: Int = 0
}