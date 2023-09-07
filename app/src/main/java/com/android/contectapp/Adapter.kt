package com.android.contectapp


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.ActivityGirdviewItemListBinding
import com.android.contectapp.databinding.ActivityRecyclerviewItemListBinding

class Adapter(val Item: MutableList<Item>, private var isGridMode: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: Item, position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    fun setGridMode(isGridMode: Boolean) {
        Log.d("정보" , "setGridMode: isGridMode=$isGridMode")
        this.isGridMode = isGridMode
        notifyDataSetChanged() // 다시 스케치 하라.
    }

    var log = "로그"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("Adapter", "onCreateViewHolder: isGridMode=$isGridMode")
        if (isGridMode) {
            val binding = ActivityGirdviewItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemGridHolder(binding)
        } else {
            val binding = ActivityRecyclerviewItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ItemListHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pos = Item[position]
        if (isGridMode) {
            if (holder is ItemGridHolder) {
                holder.name.text = pos.name
                holder.specialist.text = pos.specialist
                holder.image.setImageResource(pos.image)
                holder.itemView.setOnClickListener {
                    val position = holder.bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener?.onItemClick(pos, position)
                    }
                }
            }
        } else {
            if (holder is ItemListHolder) {
                holder.name.text = pos.name
                holder.specialist.text = pos.specialist
                holder.image.setImageResource(pos.image)
                holder.itemView.setOnClickListener {
                    val position = holder.bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener?.onItemClick(pos, position)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return Item.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ItemListHolder(val binding: ActivityRecyclerviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.recyclerviewName
        val specialist = binding.recyclerviewSpeciallist
        val image = binding.recyclerviewIvProfile
    }

    inner class ItemGridHolder(val binding: ActivityGirdviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.recyclerviewName
        val specialist = binding.recyclerviewSpeciallist
        val image = binding.recyclerviewIvProfile
    }
}
