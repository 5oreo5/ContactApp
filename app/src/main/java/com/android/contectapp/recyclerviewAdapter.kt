package com.android.contectapp


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.ActivityRecyclerviewItemListBinding
import com.android.contectapp.databinding.FragmentContactListBinding

class recyclerviewAdapter(val Item: MutableList<Item>) : RecyclerView.Adapter<recyclerviewAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }


    var itemClick: ItemClick? = null
    var log = "로그"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityRecyclerviewItemListBinding.inflate(inflater, parent, false)
        Log.d(log, "onCreateViewHolder called")
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.name.text=Item[position].name

        holder.speciallist.text=Item[position].specilalist

        Log.d(log, "onBindViewHolder called")
    }

    override fun getItemCount(): Int {
        return Item.size
    }
    override fun getItemId(position: Int):Long{
    return position.toLong()
    }

    inner class Holder(val binding: ActivityRecyclerviewItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    val speciallist=binding.recyclerviewSpeciallist
        val name=binding.recyclerviewName
    }
}



