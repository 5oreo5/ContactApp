package com.android.contectapp


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.ActivityRecyclerviewItemListBinding

class Adapter(val item: MutableList<Item>, private var isGridMode: Boolean) :
    RecyclerView.Adapter<Adapter.Holder>(), ItemTouchHelperListener {

    interface OnItemClickListener {
        fun onItemClick(data: Item, position: Int)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    fun setGridMode(gridMode: Boolean) {
        isGridMode = gridMode
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        if (isGridMode) R.layout.activity_girdview_item_list else R.layout.activity_recyclerview_item_list
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityRecyclerviewItemListBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pos = item[position]
        holder.name.text = pos.name
        holder.specialist.text = pos.specialist
        holder.image.setImageResource(pos.image)
        holder.phone.text = pos.phone

        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener?.onItemClick(pos, position)
            }
        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
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

    inner class Holder(val binding: ActivityRecyclerviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mainRv = binding.mainRv
        val name = binding.recyclerviewName
        val specialist = binding.recyclerviewSpeciallist
        val image = binding.recyclerviewIvProfile
        val phone = binding.recyclerviewMobile
    }
}