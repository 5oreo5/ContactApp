package com.android.contectapp


import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.ActivityGirdviewItemListBinding
import com.android.contectapp.databinding.ActivityRecyclerviewItemListBinding

class Adapter(val item: MutableList<Item>, private var isGridMode: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperListener {


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
        val pos = item[position]
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
                holder.phone.text = pos.phone
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
        return item.size
    }
    override fun getItemId(position: Int): Long {
        return item[position].name.hashCode().toLong()
    }
    // 아이템 드래그 시 호출
    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val name = item[from_position]
        // 리스트 갱신
        item.removeAt(from_position)
        item.add(to_position, name)

        // fromPosition에서 toPosition으로 아이템 이동 공지
        notifyItemMoved(from_position, to_position)
        return true
    }

    override fun onItemSwipe(position: Int) {
    }

    inner class ItemListHolder(val binding: ActivityRecyclerviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mainRv = binding.mainRv
        val name = binding.recyclerviewName
        val specialist = binding.recyclerviewSpeciallist
        val image = binding.recyclerviewIvProfile
        val phone = binding.recyclerviewMobile
    }
    inner class ItemGridHolder(val binding: ActivityGirdviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.recyclerviewName
        val specialist = binding.recyclerviewSpeciallist
        val image = binding.recyclerviewIvProfile
    }
}